package az.egov.service;

import az.egov.entity.Persons;
import az.egov.service.common.CRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */


public interface PersonService extends CRUDService<Persons> {

    List<Persons> getPersonList(int offset , int fetch) ;

    long totalPersonCount() ;

    public Persons findByPin(String pin) ;
}
