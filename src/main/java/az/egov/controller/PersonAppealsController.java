package az.egov.controller;

import az.egov.entity.PersonAppeals;
import az.egov.model.PersonAppealsModel;
import az.egov.service.PersonAppealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@RestController
@RequestMapping("/personappeals")
public class PersonAppealsController {

    @Autowired
    PersonAppealsService personAppealsService ;


    @GetMapping("/list")
    public List<PersonAppealsModel> getPersonAppealsList(@RequestParam("offset") Integer offset ,
                                                         @RequestParam("fetch")  Integer fetch
    )
    {

        return  personAppealsService.getPersonAppeals(offset,fetch) ;
    }

    @GetMapping("/find")
    public List<PersonAppeals> getPersonAppealslList(@RequestParam(value = "message",required = false)      String message,
                                                     @RequestParam(value = "appealTypeId",required = false) Integer appealTypeId,
                                                     @RequestParam(value = "personId",required = false)     String personId
                                                          )
    {
        return personAppealsService.extendedSearch(message,appealTypeId,personId) ;
    }


    @PostMapping("/save")
    public void savePersonAppeal(@RequestBody PersonAppeals appeals)
    {
        personAppealsService.save(appeals);
    }

}


