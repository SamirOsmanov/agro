package az.egov.controller;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.service.PersonFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 14.09.2018.
 */
@RestController
@RequestMapping("/api/personfield")
public class PersonFieldController {

   @Autowired
   private PersonFieldService personFieldService ;


   @GetMapping("/list")
   private Object getPersonFieldsList(@RequestParam("offset") Integer offset,
                                      @RequestParam("fetch")  Integer fetch)
   {
       return personFieldService.getPersonFieldsList(offset,fetch) ;
   }

   @GetMapping("/find")
   private Object findPersonFields(@RequestParam("personID") String personID)
   {
      Persons person = new Persons(personID) ;
      return personFieldService.findByPersonId(person) ;
   }


   @PostMapping("/save")
   private Object savePersonFields(@RequestBody  PersonFields personFields)
   {
      return personFieldService.save(personFields) ;
   }

   @PostMapping("/update")
   public Object updatePersonFields(@RequestBody  PersonFields personFields)
   {
      return personFieldService.update(personFields) ;
   }


}
