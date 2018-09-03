package az.egov.service;

import az.egov.entity.Persons;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

public interface PersonService {

    List<Persons> getPersonList(int offset , int fetch) ;
}
