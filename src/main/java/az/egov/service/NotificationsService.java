package az.egov.service;

import az.egov.entity.Notifications;
import az.egov.service.common.CRUDService;
import com.sun.nio.sctp.Notification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by admin on 10.09.2018.
 */

public interface NotificationsService extends CRUDService<Notifications> {

    Long totalCount() ;
}
