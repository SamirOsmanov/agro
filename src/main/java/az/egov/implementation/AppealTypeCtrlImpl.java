package az.egov.implementation;

import az.egov.entity.AppealTypeCtrl;
import az.egov.entity.Users;
import az.egov.repository.AppealTypeCtrlRepository;
import az.egov.service.AppealTypeCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 18.09.2018.
 */
@Service
public class AppealTypeCtrlImpl implements AppealTypeCtrlService {

    @Autowired
    AppealTypeCtrlRepository appealTypeRepository ;

    @Override
    public AppealTypeCtrl findByUser(Users user) {
        return appealTypeRepository.findByUser(user);
    }
}
