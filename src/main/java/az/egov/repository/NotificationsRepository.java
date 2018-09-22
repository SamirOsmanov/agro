package az.egov.repository;

import az.egov.entity.Notifications;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Notification;
import java.math.BigInteger;

/**
 * Created by admin on 10.09.2018.
 */
@Transactional
@Repository
public interface NotificationsRepository extends CrudRepository<Notifications,BigInteger> {

    @Query("select count(n) from Notifications as n where n.statusId != 3 ")
    Long totalCount() ;
}
