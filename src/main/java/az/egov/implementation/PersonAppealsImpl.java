package az.egov.implementation;

import az.egov.entity.PersonAppeals;
import az.egov.model.PersonAppealsModel;
import az.egov.repository.PersonAppealsRepository;
import az.egov.repository.PersonRepository;
import az.egov.service.PersonAppealsService;
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

    /*@Autowired
    PersonRepository personRepository ;*/


    @PersistenceContext
    private EntityManager em ;

    @Override
    public List<PersonAppealsModel> getPersonAppeals(int offset, int fetch) {
        return appealsRepository.getPersonAppeals(offset,fetch)
                                .stream()
                                .map(temp -> {
                                     return new PersonAppealsModel(  (BigInteger) temp[0],
                                                                     (Date)       temp[1],
                                                                     (String)     temp[2],
                                                                     (String)     temp[3],
                                                                     (String)     temp[4],
                                                                     (String)     temp[5],
                                                                     (String)     temp[6],
                                                                     (Integer)    temp[7],
                                                                     (String)     temp[8],
                                                                     (Integer)    temp[9],
                                                                     (String)     temp[10]

                                                                   ) ;
                                }).collect(Collectors.toList()) ;
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
    public PersonAppeals save(PersonAppeals entity) {
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
       findedAppeal.setSort(entity.getSort());
       findedAppeal.setLabel(entity.getLabel());

       return em.merge(findedAppeal) ;
    }

}
