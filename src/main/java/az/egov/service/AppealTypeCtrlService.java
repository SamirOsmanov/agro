package az.egov.service;

import az.egov.entity.AppealTypeCtrl;
import az.egov.entity.Users;
import az.egov.service.common.CRUDService;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 18.09.2018.
 */

public interface AppealTypeCtrlService  {

    AppealTypeCtrl findByUser(Users user) ;

}
