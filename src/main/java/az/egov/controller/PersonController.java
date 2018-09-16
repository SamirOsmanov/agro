package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.repository.Log4MongoRepository;

import az.egov.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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


    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/list")
    @ApiOperation(value = "Get persons list by offset and fetch parameters" ,
                  response = List.class)
    public Object personPagination(@RequestParam("offset") Integer offset ,
                                   @RequestParam("fetch")  Integer fetch ,
                                   HttpServletRequest servletRequest
                                )
    {

        System.out.println("SESSION ID : " + servletRequest.getSession().getId());
       return  personService.getPersonList(offset,fetch) ;
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
    public Object findById(@RequestParam("id") String personId)
    {
        return personService.findById(personId) ;
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
                               @RequestParam("phone") String phone)
    {
        ResponseEntity<Boolean> exchange = null ;

        RestTemplate restTemplate = new RestTemplate() ;
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
        }
    }
}

