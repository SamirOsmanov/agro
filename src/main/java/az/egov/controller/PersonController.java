package az.egov.controller;

import az.egov.entity.Persons;
import az.egov.response.ResponseEntity;
import az.egov.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonService personService ;


    @GetMapping("/list")
    public List<Persons> getPersonList(@RequestParam("offset") Integer offset ,
                                       @RequestParam("fetch")  Integer fetch
                                )
    {

       return  personService.getPersonList(offset,fetch) ;
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> savePerson(@RequestBody  Persons person)
    {
         personService.save(person);
         return null ;
    }

    @GetMapping("/find")
    public Persons findById(@RequestParam("id") String personId)
    {
        return personService.findById(personId) ;
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updatePerson(@RequestBody  Persons person)
    {

        personService.update(person);
        return null ;
    }


}

