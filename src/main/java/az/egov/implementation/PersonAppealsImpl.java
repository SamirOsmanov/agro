package az.egov.implementation;

import az.egov.entity.AppealTypeCtrl;
import az.egov.entity.AppealTypes;
import az.egov.entity.PersonAppeals;
import az.egov.entity.UserSession;
import az.egov.model.PersonAppealsModel;
import az.egov.repository.AppealTypeCtrlRepository;
import az.egov.repository.PersonAppealsRepository;
import az.egov.repository.PersonRepository;
import az.egov.repository.UserSessionRepository;
import az.egov.service.PersonAppealsService;
import az.egov.utility.helper.OperationStatus;
import az.egov.utility.validation.PropertyValidator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 03.09.2018.
 */
@Service
@Transactional
public class PersonAppealsImpl implements PersonAppealsService {

    @Autowired
    PersonAppealsRepository appealsRepository ;

    @Autowired
    UserSessionRepository userSessionRepository ;

    @Autowired
    AppealTypeCtrlRepository appealTypeCtrlRepository ;


    @PersistenceContext
    private EntityManager em ;

    @Override
    public List<PersonAppealsModel> getPersonAppeals(int offset, int fetch) {


        return em.createQuery(" from PersonAppeals as p where p.statusId != 3 ")
                              .setFirstResult(offset)
                              .setMaxResults(fetch)
                              .getResultList() ;

    }


    @Override
    public PersonAppeals findById(Object id) {
        return (PersonAppeals) em.createQuery("from PersonAppeals as p" +
                                " where p.statusId != :statusId and p.id = :id")
                               .setParameter("statusId", OperationStatus.DELETE_STATUS.getStatusId())
                               .setParameter("id",id)
                               .getResultList().get(0);
    }

    @Override
    public List<PersonAppeals> extendedSearch(String message,
                                              Integer appealTypeId,
                                              String personId)
    {
         Session session = em.unwrap(Session.class) ;

         Criteria criteria = session.createCriteria(PersonAppeals.class)
                                    .createAlias("person","p")
                                    .createAlias("appealTypes","a");

         PropertyValidator.isNull(criteria,"message",message);
         PropertyValidator.isNull(criteria,"p.id",personId);
         PropertyValidator.isNull(criteria,"a.id",appealTypeId);

         return criteria.list() ;


    }

    @Override
    public List<PersonAppeals> personAppealsByList(String sessionId) {
        UserSession session = userSessionRepository.findBySessionId(sessionId);

        return null ;
    }

    @Override
    public List<PersonAppeals> findByAppealTypes(AppealTypes appealType) {
        return appealsRepository.findByAppealTypes(appealType);
    }

    @Override
    public Iterator<PersonAppeals> findAll() {
        return appealsRepository.findAll().iterator();
    }

    @Override
    public Long totalCount() {
        return appealsRepository.totalCount() ;
    }


    @Override
    public PersonAppeals save(PersonAppeals entity)
    {
        return appealsRepository.save(entity) ;
    }

    @Override
    public PersonAppeals update(PersonAppeals entity) {
       PersonAppeals findedAppeal = appealsRepository.findById(entity.getId()).get() ;

       findedAppeal.setAppealTypes(entity.getAppealTypes());
       findedAppeal.setApplicationNumber(entity.getApplicationNumber());
       findedAppeal.setLatitude(entity.getLatitude());
       findedAppeal.setLongitude(entity.getLongitude());
       findedAppeal.setMessage(entity.getMessage());
       findedAppeal.setNote(entity.getNote());
      /* findedAppeal.setSort(entity.getSort());
       findedAppeal.setLabel(entity.getLabel());*/

       return em.merge(findedAppeal) ;
    }

}
