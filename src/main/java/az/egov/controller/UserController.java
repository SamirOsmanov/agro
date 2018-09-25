package az.egov.controller;

import az.egov.entity.*;
import az.egov.model.PersonModel;
import az.egov.repository.SmsRepository;
import az.egov.service.*;
import az.egov.utility.helper.OperationStatus;
import az.egov.utility.helper.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 12.09.2018.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    private ResponseEntity<PersonModel> exchange ;
    
    @Autowired
    private Security security ;


    @Autowired
    SmsService smsService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private  PersonService personService ;

    @Autowired
    private UserSessionService userSessionService ;

    @Autowired
    private UserRoleService userRoleService ;

    @GetMapping("/list")
    public Object getUserList(@RequestParam("offset") Integer offset,
                              @RequestParam("fetch") Integer fetch)
    {
        HashMap<String,Object> response = new HashMap<>() ;
        response.put("totalCount",userService.totalCount()) ;
        response.put("items" , userService.list(offset,fetch)) ;

        return response ;
    }


    @GetMapping("/check")
    public Object checkUser(@RequestParam("pin") String pin)
    {

        exchange = null ;

        return isPersonValid(pin) ;
    }

    public Object isPersonValid(String pin)
    {
        RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String personVerifierUrl = "http://10.255.110.1:8080/agro/api/person/info/v1?pin="+pin ;

        try
        {
            exchange = restTemplate.exchange(personVerifierUrl,
                                             HttpMethod.GET,
                                             entity,
                                             PersonModel.class);


            if (exchange == null)
                throw new NullPointerException() ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            exchange = new ResponseEntity<PersonModel>(HttpStatus.NO_CONTENT) ;
        }
        finally {
            return exchange ;
        }
    }


    @PostMapping("/login")
    public Object loginUser(@RequestBody Users user ,
                            @RequestHeader(value="User-Agent") String agent,
                            @RequestHeader(value = "source",required = false) String source ,
                            HttpServletRequest request) throws Exception {

        Boolean isUserExist = false ;
        HttpSession session = null ;

        HashMap<String,Object> response = new HashMap<>() ;

        user.setPassword( security.encrypt(user.getPassword()) );
        Users userInfo = userService.find(user.getUserName(), user.getPassword());

        String sessionID = null ;

        try {
            if (userInfo != null) {


                session = request.getSession(true);
                session.setMaxInactiveInterval(1);

                sessionID = session.getId();
                String userAgent = agent;
                Date lastActivity = new Date();
                String clientIP = request.getRemoteAddr();

                UserSession newSession = new UserSession();

                newSession.setIp(clientIP);
                newSession.setStatusId(OperationStatus.INSERT_STATUS.getStatusId());
                newSession.setLastActivity(lastActivity);
                newSession.setSessionId(sessionID);
                newSession.setUserAgent(userAgent);
                newSession.setUser(userInfo);

                if (source != null)
                {
                    newSession.setIsMobile(OperationStatus.IS_MOBILE.getStatusId());
                }
                else
                {
                    newSession.setIsMobile(OperationStatus.IS_NOT_MOBILE.getStatusId());
                }

                UserSession savedUser = userSessionService.save(newSession);
                Persons person = savedUser.getUser().getPerson();

                //UserRoles userRole = userRoleService.findByUser(savedUser.getUser());


                response.put("SID", sessionID);
                //response.put("role",userRole.getRole()) ;
                 response.put("person" , person) ;

                isUserExist = true;
            } else {
                isUserExist = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        response.put("success",isUserExist) ;

        return response ;
    }


    @GetMapping("/signout")
    public Object exitFromApp(@RequestHeader(value = "SID") String sessionID)
    {
        HashMap<String,Object> response  = new HashMap<>() ;

        try{
            userSessionService.destroySession(sessionID);
            response.put("success",true) ;
        }
        catch(Exception e)
        {
            response.put("success",false) ;
        }
        finally {
            return response ;
        }


    }


    public Object verifyPerson(  String pin,
                                 String phone,
                                 String deviceId,
                                 String firebaseId)
    {
        ResponseEntity<Boolean> exchange = null ;

        RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        HashMap<String, Object> response = new HashMap<>();

        String personVerifierUrl =  "http://10.255.110.1:8080/agro/api/person/verify/v1?pin="+pin+"&phone="+phone ;

        try
        {
            exchange = restTemplate.exchange(personVerifierUrl, HttpMethod.GET, entity, Boolean.class);

            Boolean body = exchange.getBody();

            if(body != null )
            {
                // Nomre dogurdan wexsin adinadir
                /*if(body.booleanValue()) {
                    response.put("status", true);
                    response.put("isRegistered",false) ;
                }
                else
                {
                    response.put("status", false);
                    response.put("isRegistered",false) ;
                }*/

                Persons byPin = personService.findByPin(pin);

                if(byPin != null) {


                    Users byPerson = userService.findByPerson(byPin);
                    byPerson.setFirebaseId(firebaseId);
                    byPerson.setDeviceId(deviceId);

                    userService.save(byPerson) ;

                    response.put("success",true) ;
                    response.put("isRegistered",true) ;
                    response.put("person",byPin) ;
                }
                else
                {
                    response.put("success", true);
                    response.put("isRegistered",false) ;
                }
            }
            else
            {
                response.put("success",false) ;
                response.put("isRegistered",false) ;
            }

        }
        catch (Exception e)
        {
            response.put("success",false) ;
            e.printStackTrace();

        }
        finally {
            return response ;
        }
    }


    @PostMapping("/register")
    public Object registerUser(@RequestBody Users user,
                               @RequestHeader(value = "deviceId",required = false)   String deviceId,
                               @RequestHeader(value = "firebaseId",required = false) String firebaseId) throws Exception {

        String pin   = user.getPin() ;
        String phone = user.getUserName() ;


        HashMap<String,Object> response = new HashMap<>() ;

        HashMap<String,Object> verifyResponse = (HashMap<String, Object>) verifyPerson(pin, phone,deviceId,firebaseId);
        Users result = new Users() ;

        Boolean status = (Boolean) verifyResponse.get("success");

        if(status == true)
        {

            Persons byPin = personService.findByPin(pin);

            if(byPin != null) {
                response.put("success",true) ;
                response.put("isRegistered",true) ;
                response.put("person",byPin) ;
            }
            else {
                List<Sms> smsVerify = smsService.findByPhoneAndIsVerifiedOrderByCreateDateDesc(phone,
                        OperationStatus.IS_VERIFIED.getStatusId());

                if(smsVerify != null)
                {

                    ResponseEntity<PersonModel> person = (ResponseEntity<PersonModel>) isPersonValid(pin);

                    if(person.getBody() != null) {

                        Persons newPerson = new Persons();
                        newPerson.setFatherName(person.getBody().getMiddleName());
                        newPerson.setFirstName(person.getBody().getFirstName());
                        newPerson.setLastName(person.getBody().getLastName());
                        newPerson.setPin(person.getBody().getPin());
                        newPerson.setPhone(phone);

                        Persons savedPerson = personService.save(newPerson);
                        user.setPerson(savedPerson);
                        user.setPassword( security.encrypt(user.getPassword()) ) ;

                        user.setDeviceId(deviceId);
                        user.setFirebaseId(firebaseId);

                        userService.save(user);

                        response.put("person", newPerson);
                    }
                    else
                      response.put("person",null) ;

                    response.put("isRegistered",false) ;
                    response.put("success",true) ;
                }
                else {
                    response.put("isRegistered",false) ;
                    response.put("success",false) ;
                }
            }

        }
        else
        {
            response.put("success",false) ;
            response.put("isRegistered",false) ;
        }

        return response ;

    }


    @GetMapping("/verifysms")
    public Object verifySms(@RequestParam("phone") String phone,
                             @RequestParam("code")  String code)
    {
        return isSmsVerified(phone,code) ;
    }

    private Object isSmsVerified(    String phone,
                                     String code)
    {
        HashMap<String,Boolean> response = new HashMap<>() ;

        RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String verifysmsURL = "http://10.255.110.1:8080/agro/api/sms/verify/v1?phone="+phone+"&code="+code ;

        try
        {
            ResponseEntity<Boolean> exchange = restTemplate.exchange(verifysmsURL,
                    HttpMethod.POST,
                    entity,
                    Boolean.class);


            if (exchange == null)
                response.put("succes",false) ;
            else
            {
                if(exchange.getBody() == true)
                {


                    List<Sms> smsList = smsService.findByPhoneAndIsVerifiedOrderByCreateDateDesc(phone,OperationStatus.IS_NOT_VERIFIED.getStatusId());

                    if(smsList !=  null)
                    {
                        Sms sms = smsList.get(0) ;
                        sms.setIsVerified(OperationStatus.IS_VERIFIED.getStatusId());

                        smsService.save(sms) ;
                        response.put("success",true) ;
                    }
                    else
                    {
                        response.put("success",false) ;
                    }

                }
                else
                {

                    response.put("success",false) ;
                }



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }

    @GetMapping("/sendsms")
    public Object sendSms(@RequestParam("phone") String phone)
    {
        HashMap<String,Boolean> response = new HashMap<>() ;

        RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String sendSmsURL = "http://10.255.110.1:8080/agro/api/sms/send/v1?phone="+phone ;

        try
        {
            ResponseEntity<Boolean>  exchange = restTemplate.exchange(sendSmsURL,
                                                                      HttpMethod.POST,
                                                                      entity, Boolean.class);


            if (exchange == null)
                response.put("success",false) ;
            else
            {
                if(exchange.getBody() == true)
                {
                    response.put("success",true) ;
                    Sms sms = new Sms(phone) ;
                    sms.setIsVerified(OperationStatus.IS_NOT_VERIFIED.getStatusId());

                    smsService.save(sms) ;
                }
                else
                {
                    response.put("success",false) ;
                }

            }
        }
        catch (Exception e)
        {
            response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }

}
