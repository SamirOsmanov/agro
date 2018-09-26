package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.entity.Users;
import az.egov.model.Contact;
import az.egov.repository.Log4MongoRepository;

import az.egov.service.PersonService;
import az.egov.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by admin on 03.09.2018.
 */

@RestController
@RequestMapping("/api/person")
@Api(value="Person Controller",
     description="Controller for Person related operations")
public class PersonController {


    @Autowired
    PersonService personService ;

    @Autowired
    UserService userService ;


    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/list")
    @ApiOperation(value = "Get persons list by offset and fetch parameters" ,
                  response = List.class)
    public Object personPagination(@RequestParam("offset") Integer offset ,
                                   @RequestParam("fetch")  Integer fetch ,
                                   HttpServletRequest servletRequest
                                )
    {

       return  personService.getPersonList(offset,fetch) ;
    }

    @PostMapping("/addinfo")
    public Object savePersonAdditionalInfo(@RequestBody HashMap<String,Object> request)
    {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            String personId      = (String)request.get("id") ;
            List contactList     = (List)  request.get("contacts");
            String address       = (String)request.get("address") ;
            Integer address_type = (Integer)request.get("addressType") ;

            personService.savePersonAddress(personId,address_type,address);

            for (Object contact : contactList)
            {
                HashMap<String,Object> data = (HashMap<String, Object>) contact;
                personService.savePersonContacts(personId,
                                                (Integer) data.get("id"),
                                                (String)data.get("value"));
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

    @PostMapping("/updateactivity")
    public Object updateActivity(@RequestBody HashMap<String,Object> request) {

        HashMap<String,Object> response = new HashMap<>() ;

        try
        {

            String personId = (String)request.get("id") ;
            List<Integer> activityList = (List<Integer>)request.get("activityAreasList");
            Integer areaId = (Integer)request.get("areaId") ;

            personService.deletePersonActivity(personId);

            for (Integer activity : activityList)
            {
                personService.savePersonActivity(personId,activity,areaId);
            }

            response.put("success",true) ;
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


    @PostMapping("/addactivity")
    public Object addActivity(@RequestBody HashMap<String,Object> request)
    {

        HashMap<String,Object> response = new HashMap<>() ;

        try
        {

            String personId = (String)request.get("id") ;
            List<Integer> activityList = (List<Integer>)request.get("activityAreasList");
            Integer areaId = (Integer)request.get("areaId") ;

            for (Integer activity : activityList)
            {
               personService.savePersonActivity(personId,activity,areaId);
            }


            response.put("success",true) ;
        }
        catch (Exception e)
        {
            response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }


    @PostMapping("/save")
    @ApiOperation(value = "Insert new Person to the database" ,
                  response = Persons.class)
    public Object savePerson(@RequestBody  Persons person)
    {
        return personService.save(person);
    }

    @GetMapping("/find")
    @ApiOperation(value = "Find Person by unique identifier",
                  response = Persons.class)
    public Object findById(@RequestParam(value = "id",required = false)      String personId,
                           @RequestParam(value = "pin",required = false)     String pin ,
                           @RequestParam(value = "name",required = false)    String name,
                           @RequestParam(value = "surname",required = false) String surname,
                           @RequestParam(value = "father",required = false)  String fathername)
    {
        try {
            return personService.extendedSearch(personId, pin, name, surname, fathername);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null ;
    }

    @GetMapping("/{id}")
    public Object getPersonById(@PathVariable("id") String id )
    {
        return personService.findById(id) ;
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update existing person information" ,
                  response = Persons.class)
    public Object updatePerson(@RequestBody  Persons person)
    {
        return personService.update(person);
    }


    @GetMapping("/verify")
    public Object verifyPerson(@RequestParam("pin") String pin,
                               @RequestParam("phone") String phone,
                               @RequestHeader(value = "deviceId",required = false) String deviceId,
                               @RequestHeader(value = "firebaseId",required = false) String firebaseId)
    {
        ResponseEntity<Boolean> exchange = null ;

        return verifyPersonExists(pin,phone,deviceId,firebaseId) ;

        /*RestTemplate restTemplate = new RestTemplate() ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","1111");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        HashMap<String, Object> response = new HashMap<>();

        String personVerifierUrl =  "http://10.255.110.1:8080/agro/api/person/verify/v1?pin="+pin+"&phone="+phone ;

        LOGGER.info("URL : " + personVerifierUrl);


        try
        {
            exchange = restTemplate.exchange(personVerifierUrl, HttpMethod.GET, entity, Boolean.class);

            Boolean body = exchange.getBody();

            if(body != null )
            {
                if(body.booleanValue()) {
                    Persons person = personService.findByPin(pin);
                    response.put("status", true);
                    response.put("isRegistered",true) ;
                    response.put("person", person);
                }
                else
                {
                    response.put("status", false);
                    response.put("isRegistered",false) ;
                }
            }
            else
            {
                response.put("status",false) ;
                response.put("isRegistered",false) ;
            }


            LOGGER.info(" BODY : " + exchange.getStatusCode() + ", " + exchange.getBody());

        }
        catch (Exception e)
        {
            LOGGER.info("EXCEPTION :  " + e.getMessage());
            response.put("status",false) ;
            e.printStackTrace();


        }
        finally {
            return response ;
        }*/
    }




    public Object verifyPersonExists(  String pin,
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
                if(body.booleanValue()) {

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
                    response.put("success", false);
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
}

