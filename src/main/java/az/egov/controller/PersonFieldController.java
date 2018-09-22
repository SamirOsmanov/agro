package az.egov.controller;

import static az.egov.utility.helper.OperationStatus.* ;
import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.implementation.PersonFieldsImpl;
import az.egov.service.PersonFieldService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
      HashMap<String,Object> response  = new HashMap<>() ;
      response.put("items",personFieldService.getPersonFieldsList(offset,fetch));
      response.put("totalCount",personFieldService.totalCount()) ;

      return response ;
   }

   @GetMapping("/find")
   private Object findPersonFields(@RequestParam("personID") String personID)
   {

      Persons person = new Persons(personID) ;

      return  personFieldService.findByPersonAndStatusId(person,INSERT_STATUS.getStatusId())  ;
   }

   @GetMapping("/{id}")
   public Object getPersonFieldById(@PathVariable("id") Integer id)
   {
      return personFieldService.findById(id) ;
   }

   @DeleteMapping("/delete/{id}")
   private Object deletePersonField(@PathVariable("id") Integer id)
   {
      HashMap<String,Object> response = new HashMap<>() ;

      try {
         PersonFields personField = personFieldService.findById(id);
         personField.setStatusId(DELETE_STATUS.getStatusId());
         personFieldService.save(personField);

         response.put("succes",true) ;
         response.put("id",id) ;
      }
      catch(Exception e)
      {
         response.put("succes",false) ;
      }

      return response ;
   }

   @PostMapping("/save")
   private Object savePersonFields(@RequestBody  PersonFields personFields)
   {

      personFields.setStatusId(INSERT_STATUS.getStatusId());
      HashMap<String,Object> response = new HashMap<>() ;

      try
      {
         personFieldService.save(personFields) ;
         response.put("success",true) ;
      }
      catch(Exception e)
      {
         response.put("success",false) ;
      }

      return response ;
   }

   @PostMapping("/update")
   public Object updatePersonFields(@RequestBody  PersonFields personFields)
   {
      personFields.setStatusId(UPDATE_STATUS.getStatusId());
      HashMap<String,Object> response = new HashMap<>() ;

       try
       {
          personFieldService.update(personFields) ;
          response.put("success",true) ;
       }
       catch (Exception e)
       {
          response.put("success",false) ;
       }

       return response ;

   }


}
