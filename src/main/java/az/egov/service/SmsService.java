package az.egov.service;

import az.egov.entity.Sms;
import az.egov.service.common.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 21.09.2018.
 */
@Service
public interface SmsService extends CRUDService<Sms> {

    List<Sms> findByPhoneAndIsVerifiedOrderByCreateDateDesc(String phone,Integer isVerified) ;

}
