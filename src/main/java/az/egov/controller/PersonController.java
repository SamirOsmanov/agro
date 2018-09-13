package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.repository.Log4MongoRepository;

import az.egov.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

@RestController
@RequestMapping("/agroculture/person")
@Api(value="Person Controller",
     description="Controller for Person related operations")
public class PersonController {


    @Autowired
    PersonService personService ;




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

        String personVerifierUrl = "http://192.168.112.19:8084/agro/api/person/verify/v1?pin="+pin+"&phone="+phone ;


        try
        {
            exchange = restTemplate.exchange(personVerifierUrl, HttpMethod.GET, entity, Boolean.class);

            if (exchange == null)
                throw new NullPointerException() ;
        }
        catch (NullPointerException e)
        {
            exchange = new ResponseEntity<Boolean>(HttpStatus.GATEWAY_TIMEOUT) ;
        }
        finally {
            return exchange ;
        }
    }
}

