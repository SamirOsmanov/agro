package az.egov.repository;

import az.egov.entity.Notification;
import az.egov.entity.NotificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by admin on 26.09.2018.
 */
@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification,BigInteger> {

    //@Query("select count(n) from Notification as n where n.status.id != 3 ")
    @Query("select count(n) from Notification as n ")
    Long totalCount() ;



}
