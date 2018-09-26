package az.egov.controller;

import static az.egov.utility.helper.OperationStatus.* ;
import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.entity.Status;
import az.egov.implementation.PersonFieldsImpl;
import az.egov.service.PersonFieldService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 14.09.2018.
 */
@RestController
@RequestMapping("/api/personfield")
public class PersonFieldController {

   @Autowired
   PersonFieldService personFieldService ;


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

      HashMap<String,Object> response = new HashMap<>() ;

      try {

         List<PersonFields> byPersonAndStatus = personFieldService.findByPersonAndStatus(person, INSERT_STATUS.getStatusId());
         response.put("items",byPersonAndStatus) ;
         response.put("sucess",true) ;
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

         Status status = new Status(DELETE_STATUS.getStatusId());

         personField.setStatus(status);
         personFieldService.save(personField);

         response.put("succes",true) ;
         response.put("id",id) ;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         response.put("succes",false) ;
      }

      return response ;
   }

   @PostMapping("/save")
   private Object savePersonFields(@RequestBody  PersonFields personFields)
   {

      Status status = new Status(INSERT_STATUS.getStatusId());

      personFields.setStatus(status);
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

      Status  status = new Status(UPDATE_STATUS.getStatusId()) ;

      personFields.setStatus(status);
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
