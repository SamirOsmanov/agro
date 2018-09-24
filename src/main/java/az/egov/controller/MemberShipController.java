package az.egov.controller;

import az.egov.entity.MemberShips;
import az.egov.entity.Status;
import az.egov.service.MemberShipService;
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
           memberShip.setStatus(status); ;
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
