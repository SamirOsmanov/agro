package az.egov.controller;

import az.egov.model.PersonAppealsModel;
import az.egov.service.PersonAppealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@RestController
@RequestMapping("/personappeals")
public class PersonAppealsController {

    @Autowired
    PersonAppealsService personAppealsService ;


    @GetMapping("/list/{offset}/{fetch}")
    public List<PersonAppealsModel> getPersonAppealsList(@PathVariable(value = "offset") Integer offset ,
                                                         @PathVariable(value = "fetch")  Integer fetch
    )
    {

        return  personAppealsService.getPersonAppeals(offset,fetch) ;
    }

}


