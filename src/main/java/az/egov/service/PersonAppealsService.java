package az.egov.service;


import az.egov.model.PersonAppealsModel;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
public interface PersonAppealsService {

    List<PersonAppealsModel> getPersonAppeals(int offset , int fetch) ;
}
