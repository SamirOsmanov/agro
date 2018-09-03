package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonService personService ;


    @GetMapping("/list/{offset}/{fetch}")
    public List<Persons> getPersonList(@PathVariable(value = "offset") Integer offset ,
                                       @PathVariable(value = "fetch")  Integer fetch
                                )
    {

       return  personService.getPersonList(offset,fetch) ;
    }


}

