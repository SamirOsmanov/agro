package az.egov.service;

import az.egov.entity.NotificationPriorities;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

public interface NotificationPriorityService {

    List<NotificationPriorities> findAll() ;
}
