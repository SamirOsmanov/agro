package az.egov.repository;

import az.egov.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 21.09.2018.
 */
@Repository
@Transactional
public interface SmsRepository extends JpaRepository<Sms,Integer> {

    List<Sms> findByPhoneAndIsVerifiedOrderByCreateDateDesc(String phone, Integer isVerified) ;

    List<Sms> findByPhoneOrderByCreateDateDesc(String phone) ;
}
