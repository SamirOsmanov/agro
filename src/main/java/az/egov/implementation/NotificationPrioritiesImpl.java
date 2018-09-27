package az.egov.implementation;

import az.egov.entity.NotificationPriorities;
import az.egov.repository.NotificationPrioritiesRepository;
import az.egov.service.NotificationPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

@Service
public class NotificationPrioritiesImpl implements NotificationPriorityService {

    @Autowired
    NotificationPrioritiesRepository prioritiesRepository ;

    @Override
    public List<NotificationPriorities> findAll() {
        return prioritiesRepository.findAll();
    }
}
