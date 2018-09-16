package az.egov.controller;

import az.egov.entity.AppealTypes;
import az.egov.entity.RequestTypes;
import az.egov.service.AppealTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 11.09.2018.
 * \
 *
 */
@RestController
@RequestMapping("/api/appealtypes")
public class AppealTypesController {

    @Autowired
    private AppealTypeService appealTypesService ;

    @GetMapping("/find")
    public Object findByRequestId(@RequestParam(value = "statusId",required = false)      Integer statusId ,
                                  @RequestParam(value = "requestTypeId",required = true) Integer requestTypeId,
                                  @RequestParam(value = "parentId",required = false) Integer parentId
                                  )
    {

       return appealTypesService.extendedSearch(statusId,requestTypeId,parentId) ;
    }
}
