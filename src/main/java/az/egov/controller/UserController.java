package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.entity.UserSession;
import az.egov.entity.Users;
import az.egov.model.PersonModel;
import az.egov.service.PersonService;
import az.egov.service.UserService;
import az.egov.service.UserSessionService;
import az.egov.utility.helper.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by admin on 12.09.2018.
 */

@RestController
@RequestMapping("/agroculture/user")
public class UserController {

    private ResponseEntity<PersonModel> exchange ;
    
    @Autowired
    private Security security ;


    @Autowired
    private UserService userService ;

    @Autowired
    private UserSessionService userSessionService ;

    @GetMapping("/check")
    public Object checkUser(String pin)
    {

        RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String personVerifierUrl = "http://192.168.112.19:8084/agro/api/person/info/v1?pin="+pin ;

        try
        {
            exchange = restTemplate.exchange(personVerifierUrl, HttpMethod.GET, entity, PersonModel.class);

            System.out.println("Model : " + exchange.getBody());
            if (exchange == null)
                throw new NullPointerException() ;
        }
        catch (NullPointerException e)
        {
            exchange = new ResponseEntity<PersonModel>(HttpStatus.NO_CONTENT) ;
        }
        finally {
            return exchange ;
        }
    }


    @PostMapping("/login")
    public Object loginUser(@RequestBody Users user ,
                            @RequestHeader(value="User-Agent") String agent,
                            HttpServletRequest request) throws Exception {

        Boolean isUserExist = false ;
        HttpSession session = request.getSession(false) ;

        user.setPassword( security.encrypt(user.getPassword()) );
        Users userInfo = userService.find(user.getUserName(), user.getPassword());

        if( session == null)
        {
            session = request.getSession();

            String sessionID  = session.getId() ;
            String userAgent  = agent ;
            Date lastActivity = new Date() ;
            String clientIP   = request.getRemoteAddr();



            if(userInfo != null)
            {
                UserSession newSession = new UserSession() ;

                newSession.setIp(clientIP);
                newSession.setLastActivity(lastActivity);
                newSession.setSessionId(sessionID);
                newSession.setUserAgent(userAgent);
                newSession.setUser(userInfo);

                userSessionService.save(newSession) ;

                isUserExist = true ;
            }
            else
            {
                isUserExist = false ;
            }
        }
        else if(userInfo != null)
        {
            isUserExist = true ;
        }
        else
        {
            isUserExist = false ;
        }



        return isUserExist ;
    }

    @PostMapping("/register")
    public Object registerUser(@RequestBody Users user) throws Exception {

        ResponseEntity<PersonModel> responseEntity = (ResponseEntity<PersonModel>) checkUser(user.getPin());
        Users result = new Users() ;

      /* // ---------------------
        user.setPassword( security.encrypt(user.getPassword()) );
        result = userService.save(user) ;
        //------------------------*/

        if(responseEntity != null )
        {
            if(responseEntity.getBody() != null)
            {
                user.setPassword( security.encrypt(user.getPassword()) );
                result = userService.save(user,responseEntity.getBody()) ;
            }

        }

        return result ;
    }


}
