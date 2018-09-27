package az.egov.service;

import az.egov.entity.NotificationSentTypes;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */
public interface NotificationSentTypeService {

   List<NotificationSentTypes> findAll() ;
}
