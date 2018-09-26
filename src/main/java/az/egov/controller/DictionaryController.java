package az.egov.controller;

import az.egov.repository.AddressTypesRepository;
import az.egov.service.ActivityAreaService;
import az.egov.service.ContactService;
import az.egov.service.MembershipTypeService;
import az.egov.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
            response.put("success",false) ;
        }
        finally {
            return response ;
        }
    }
}
