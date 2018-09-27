package az.egov.implementation;

import az.egov.entity.NotificationSentTypes;
import az.egov.repository.NotificationSentTypesRepository;
import az.egov.service.NotificationSentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

@Service
public class NotificationSentTypeImpl implements NotificationSentTypeService {


    @Autowired
    NotificationSentTypesRepository sentTypeRepository ;

    @Override
    public List<NotificationSentTypes> findAll() {
        return sentTypeRepository.findAll();
    }
}
