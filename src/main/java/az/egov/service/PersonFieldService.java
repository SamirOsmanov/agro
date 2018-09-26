package az.egov.service;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.entity.Status;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 14.09.2018.
 */

public interface PersonFieldService extends CRUDService<PersonFields>
{
    List<PersonFields> getPersonFieldsList(int offset , int fetch) ;

    List<PersonFields> findByPersonAndStatus(Persons person,Integer status) ;

    Long totalCount() ;
}
