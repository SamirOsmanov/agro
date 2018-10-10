package az.egov.implementation;

import az.egov.entity.Sms;
import az.egov.repository.SmsRepository;
import az.egov.service.SmsService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 21.09.2018.
 */

@Service
public class SmsImpl implements SmsService {

    @Autowired
    private SmsRepository smsRepository ;

    @Override
    public Sms save(Sms entity) {
        return smsRepository.save(entity);
    }

    @Override
    public List<Sms> findByPhoneAndIsVerifiedOrderByCreateDateDesc(String phone,Integer isVerified) {
        return smsRepository.findByPhoneAndIsVerifiedOrderByCreateDateDesc(phone,
                                                                           isVerified);
    }

    @Override
    public List<Sms> findByPhoneOrderByCreateDateDesc(String phone) {
        return smsRepository.findByPhoneOrderByCreateDateDesc(phone);
    }
}
