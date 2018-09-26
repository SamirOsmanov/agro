package az.egov.repository;

import az.egov.entity.Notification;
import az.egov.entity.NotificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 26.09.2018.
 */
@Repository
@Transactional
public interface NotificationDetailsRepository extends JpaRepository<NotificationDetails,Integer> {

    NotificationDetails findByNotification(Notification notification) ;
}
