package az.egov.controller;

import az.egov.entity.*;
import az.egov.repository.PersonRepository;
import az.egov.service.MemberShipService;
import az.egov.service.PersonMembershipService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static az.egov.utility.helper.OperationStatus.* ;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 24.09.2018.
 */
@RestController
@RequestMapping("/api/membership")
public class MemberShipController {

   @Autowired
   private MemberShipService memberShipsService ;

   @Autowired
   private PersonMembershipService personMembershipService ;

   @Autowired
   private PersonRepository personsRepository ;


    @GetMapping("/list")
    public Object list(@RequestParam("offset") Integer offset,
                       @RequestParam("fetch") Integer fetch)
    {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            Status status = new Status(OperationStatus.DELETE_STATUS.getStatusId()) ;

            List<MemberShips> memberShipList = memberShipsService.getMemberShipList(offset, fetch);
            response.put("items", memberShipList) ;
            response.put("success",true) ;
            response.put("totalCount",memberShipsService.totalCount(status)) ;
        }
        catch (Exception e)
        {
           response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }

    @GetMapping("/find")
    public Object extendedSearchMemberShips(@RequestParam(name = "name" ,
                                            required = false) String name,
                                            @RequestParam(name = "memberShipTypeID",required = false)
                                            Integer memberShipTypeID,
                                            @RequestParam(name = "activityAreaID",required = false)
                                            Integer activityAreaID)
    {
        HashMap<String,Object> response = new HashMap<>() ;
        Status status = new Status(OperationStatus.DELETE_STATUS.getStatusId()) ;

        try
        {
            List<MemberShips> memberShips = memberShipsService.extendedSearch(name,
                                                                              memberShipTypeID,
                                                                              activityAreaID);

            response.put("totalCount",memberShipsService.totalCount(status));
            response.put("items",memberShips) ;
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

    @GetMapping("/{id}")
    public Object showMembership(@PathVariable("id") Integer id)
    {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            MemberShips memberShipbyId = memberShipsService.findById(id);
            response.put("item",memberShipbyId) ;
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

    @PutMapping("/update")
    public Object updateMembership(@RequestBody  MemberShips memberShip)
    {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            MemberShips memberShipbyId = memberShipsService.findById(memberShip.getId());

            memberShipbyId.setStatus(memberShip.getStatus());
            memberShipbyId.setAddress(memberShip.getAddress());
            memberShipbyId.setName(memberShip.getName());
            memberShipbyId.setPhoneNumber(memberShip.getPhoneNumber());
            memberShipbyId.setActivityAreas(memberShip.getActivityAreas());
            memberShipbyId.setMemberShipTypes(memberShip.getMemberShipTypes());

            memberShipsService.update(memberShipbyId) ;

            response.put("success",true) ;
            response.put("id",memberShip.getId()) ;
        }
        catch (Exception e)
        {
            response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }


   @DeleteMapping("/deleteperson")
   public Object removePersonFromMembership(@RequestBody HashMap<String,Object> params)
   {
       HashMap<String,Object> response = new HashMap<>() ;


       try {
           Integer memberShipID = (Integer) params.get("memberShipID");
           String pin           = (String) params.get("pin");

           MemberShips memberShips = new MemberShips(memberShipID);
           Status status = new Status(OperationStatus.DELETE_STATUS.getStatusId());

           PersonMemberships personMemberships = new PersonMemberships();
           Persons person = personsRepository.findByPin(pin);


           PersonMemberships byPersonAndMemberShips = personMembershipService.findByPersonAndMemberShips(person, memberShips);
           byPersonAndMemberShips.setStatus(status);

           personMembershipService.save(byPersonAndMemberShips);

           response.put("success", true);
       }
       catch (Exception e)
       {
           response.put("success", false);
       }
       finally {
           return response ;
       }


   }

   @PostMapping("/addperson")
   public Object addPersonIntoMemberShip(@RequestBody HashMap<String,Object> params)
   {
       HashMap<String,Object> response = new HashMap<>() ;

       try {

           Integer positionID   = (Integer) params.get("positionID") ;
           Integer memberShipID = (Integer) params.get("memberShipID");
           String  pin          = (String)  params.get("pin") ;

           MemberShips memberShips = new MemberShips(memberShipID);
           Status status = new Status(OperationStatus.INSERT_STATUS.getStatusId());

           PersonMemberships personMemberships = new PersonMemberships();
           Positions position = new Positions(positionID) ;

           Persons person = personsRepository.findByPin(pin) ;

           personMemberships.setPerson(person);
           personMemberships.setPosition(position);

           personMemberships.setMemberShips(memberShips);
           personMemberships.setStatus(status);

           PersonMemberships saved = personMembershipService.save(personMemberships);

           response.put("id",saved.getId()) ;
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


   @DeleteMapping("/delete/{id}")
   public Object deleteMemberShip(@PathVariable("id") Integer id)
   {
       HashMap<String,Object> response = new HashMap<>() ;

       try
       {
           Status status = new Status(OperationStatus.DELETE_STATUS.getStatusId()) ;
           MemberShips memberShipbyId = memberShipsService.findById(id);
           memberShipbyId.setStatus(status);

           memberShipsService.update(memberShipbyId) ;

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
   public Object saveMemberShip(@RequestBody  MemberShips memberShip)
   {
       HashMap<String,Object> response = new HashMap<>() ;

       try {

           Status status = new Status(INSERT_STATUS.getStatusId()) ;
           memberShip.setStatus(status);

           Integer id = memberShipsService.save(memberShip).getId();

           response.put("id",id) ;
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
}
