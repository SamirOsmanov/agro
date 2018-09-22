package az.egov.controller;

import az.egov.entity.*;
import az.egov.service.AppealTypeCtrlService;
import az.egov.service.PersonAppealsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 03.09.2018.
 */
@RestController
@RequestMapping("/api/personappeals")
@Api(value = "PersonAppeal Controller" ,
     description = "Controller for PersonAppeal related operations")
public class PersonAppealsController {

    @Autowired
    AppealTypeCtrlService appealTypesCtrlService ;

    @Autowired
    PersonAppealsService personAppealsService ;

   /* @Autowired
    UserSessionService userSessionService ;

    @Autowired
    UserRoleService userRolesService ;*/


    @GetMapping("/list")
    @ApiOperation(value = "Get person appeal list by offset and fetch parameters" ,
                 response = List.class)
    public Object personAppealsPagination(@RequestParam("offset") Integer offset ,
                                                         @RequestParam("fetch")  Integer fetch
    )
    {
        Map<String,Object> listMap = new HashMap<>() ;
        listMap.put("items",personAppealsService.getPersonAppeals(offset,fetch)) ;
        listMap.put("totalCount",personAppealsService.totalCount()) ;
        return   listMap;
    }

    @GetMapping("/find")
    @ApiOperation(value = "Extended search for person appeal. Required minimum one parameter " ,
                  response = List.class)
    public Object findPersonAppeals(@RequestParam(value = "message",required = false)      String message,
                                                     @RequestParam(value = "appealTypeId",required = false) Integer appealTypeId,
                                                     @RequestParam(value = "personId",required = false)     String personId
                                                          )
    {
        return personAppealsService.extendedSearch(message,appealTypeId,personId) ;
    }

    @GetMapping("/{id}")
    public Object getPersonAppealsById(@PathVariable("id") BigInteger id)
    {
        return personAppealsService.findById(id) ;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Insert new person appeal to the database" ,
                  response = PersonAppeals.class)
    public Object savePersonAppeal(@RequestBody PersonAppeals appeals)
    {
        PersonAppeals savedAppeal = personAppealsService.save(appeals);

        HashMap<String,Object> response = new HashMap<>() ;
        response.put("appealId",savedAppeal.getId()) ;

        return response ;
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update existing person appeals information" ,
                  response = PersonAppeals.class)
    public Object updatePersonAppeal(@RequestBody PersonAppeals appeals)
    {
        return personAppealsService.update(appeals);
    }


    @GetMapping("/changestatus")
    public Object changeAppealStatus(@RequestParam("id") Integer id ,
                                     @RequestParam("solvedId") Integer solvedId)
    {
       HashMap<String,Object> response = new HashMap<>() ;

       /*try {
           Solve solve = new Solve(solvedId);
           PersonAppeals appeal = personAppealsService.findById(id);
           appeal.setSolve(solve);

           personAppealsService.save(appeal);

           response.put("success",true) ;
       }
       catch (Exception e)
       {
           response.put("success",false) ;
       }*/

       return response ;
    }

   /* @GetMapping("/listbyroles")
    public Object personAppealsListByRole(@RequestHeader("SID") String sessionID)
    {

        Iterator<PersonAppeals> personAppealsList = null ;

        UserSession session = userSessionService.findBySessionIdAndStatusId(sessionID, ACTIVE_USER_STATUS.getStatusId());
        Users user = session.getUser() ;
        UserRoles role =  userRolesService.findByUser(user); // 4


        Roles roles = Roles.valueOf(role.getRole().getName().toUpperCase());

        switch (roles)
        {
            case CONTROLLER :

               AppealTypeCtrl appealType = appealTypesCtrlService.findByUser(user) ;
               personAppealsList = personAppealsService.findByAppealTypes(appealType.getAppealType()).iterator() ;

                break ;
            case MODERATOR :
                personAppealsList = personAppealsService.findAll() ;
                break ;
            case ADMIN:

                break;

        }

        return personAppealsList ;

    }*/

}


