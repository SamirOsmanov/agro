package az.egov.controller;

import az.egov.entity.Areas;
import az.egov.repository.AddressTypesRepository;
import az.egov.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by admin on 25.09.2018.
 */

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {


    @Autowired
    PositionService positionService ;

    @Autowired
    ActivityAreaService activityAreaService ;

    @Autowired
    ContactService contactService ;

    @Autowired
    MembershipTypeService memberShipTypeService ;

    @Autowired
    AddressTypesRepository addressTypesRepository ;

    @Autowired
    NotificationSentTypeService sentTypeService ;

    @Autowired
    NotificationPriorityService priorityService ;

    @Autowired
    AreaService areaService ;

    @Autowired
    PersonActivityAreaService personActivityAreaService ;


    @GetMapping("/area/{id}/activity")
    public Object getActivityByArea(@PathVariable("id") Integer id)
    {
        Areas area = new Areas(id) ;

        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            response.put("items",personActivityAreaService.findAactivityByAreaId(area));
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


    @GetMapping("/area")
    public Object getAreas()
    {
        Integer areaTypeId = 3 ;
        Integer parentId   = 95 ;

        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",areaService.findAll(areaTypeId,parentId)) ;
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

    @GetMapping("/priority")
    public Object getPriorities()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",priorityService.findAll()) ;
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


    @GetMapping("/senttype")
    public Object getSentTypes()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",sentTypeService.findAll()) ;
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

    @GetMapping("/activity")
    public Object getActivityAreas()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",activityAreaService.findAll()) ;
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

    @GetMapping("/address")
    public Object getAdsresses()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",addressTypesRepository.findAll()) ;
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



    @GetMapping("/membership")
    public Object getMembershipTypesList()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",memberShipTypeService.findAll()) ;
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


    @GetMapping("/contact")
    public Object getContactList()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",contactService.findAll()) ;
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



    @GetMapping("/positions")
    public Object getPositionsList()
    {
        HashMap<String,Object> response = new HashMap<>() ;
        try {
            response.put("items",positionService.findAll()) ;
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
}
