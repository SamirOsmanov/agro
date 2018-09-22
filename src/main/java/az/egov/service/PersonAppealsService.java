package az.egov.service;


import az.egov.entity.AppealTypes;
import az.egov.entity.PersonAppeals;
import az.egov.model.PersonAppealsModel;
import az.egov.service.common.CRUDService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

public interface PersonAppealsService extends CRUDService<PersonAppeals> {

    List<PersonAppealsModel> getPersonAppeals(int offset , int fetch) ;

    List<PersonAppeals> extendedSearch(String message,
                                       Integer appealTypeId,
                                       String personId) ;

    List<PersonAppeals> personAppealsByList(String sessionId) ;

    List<PersonAppeals> findByAppealTypes(AppealTypes appealType) ;

    Iterator<PersonAppeals> findAll() ;

    Long totalCount() ;
}
