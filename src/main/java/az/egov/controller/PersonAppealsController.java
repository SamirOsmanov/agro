package az.egov.controller;

import az.egov.entity.PersonAppeals;
import az.egov.model.PersonAppealsModel;
import az.egov.service.PersonAppealsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@RestController
@RequestMapping("/api/personappeals")
@Api(value = "PersonAppeal Controller" ,
     description = "Controller for PersonAppeal related operations")
public class PersonAppealsController {

    @Autowired
    PersonAppealsService personAppealsService ;


    @GetMapping("/list")
    @ApiOperation(value = "Get person appeal list by offset and fetch parameters" ,
                 response = List.class)
    public Object personAppealsPagination(@RequestParam("offset") Integer offset ,
                                                         @RequestParam("fetch")  Integer fetch
    )
    {

        return  personAppealsService.getPersonAppeals(offset,fetch) ;
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

}


