package az.egov.implementation;

import az.egov.entity.PersonAppeals;
import az.egov.entity.Persons;
import az.egov.repository.Log4MongoRepository;
import az.egov.repository.PersonRepository;
import az.egov.response.ResponseEntity;
import az.egov.service.PersonService;
import az.egov.utility.helper.OperationStatus;
import az.egov.utility.validation.PropertyValidator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

@Service
public class PersonImpl implements PersonService {

    @Autowired
    PersonRepository personRepository ;


    @PersistenceContext
    EntityManager em ;

    @Override
    public List<Persons> getPersonList(int offset, int fetch)
    {

        return  personRepository.getPersonList(offset,fetch) ;
    }

    @Override
    public long totalPersonCount() {
        return personRepository.count() ;
    }

    @Override
    public Persons findByPin(String pin) {
        return personRepository.findByPin(pin) ;
    }

    @Override
    public List<Persons> extendedSearch(String personId,
                                  String pin,
                                  String name,
                                  String surname,
                                  String fathername) {

        Session session = em.unwrap(Session.class) ;

        Criteria criteria = session.createCriteria(Persons.class) ;


        PropertyValidator.isNull(criteria,"id",personId);
        PropertyValidator.isNull(criteria,"pin",pin);
        PropertyValidator.like(criteria,"firstName",name);
        PropertyValidator.like(criteria,"lastName",surname);
        PropertyValidator.like(criteria,"fatherName",fathername);

        criteria.add(Restrictions.ne("statusId", OperationStatus.DELETE_STATUS.getStatusId())) ;

        return criteria.list() ;
    }

    @Override
    public Integer savePersonActivity(String personId, Integer activityId , Integer areaId) {
      return   personRepository.savePersonActivity(personId,activityId,areaId);
    }

    @Override
    public void deletePersonActivity(@Param("personId") String personId) {
        personRepository.deletePersonActivity(personId);
    }

    @Override
    public void savePersonContacts(String personId, Integer contactTypeId, String contactInfo) {
        personRepository.savePersonContacts(personId,
                                            contactTypeId,
                                            contactInfo);
    }

    @Override
    public void savePersonAddress(String personId, Integer addressTypeId, String address) {
        personRepository.savePersonAddress(personId,addressTypeId,address);
    }


    @Override
    public Persons save(Persons entity) {
        return personRepository.save(entity)  ;
    }


    @Override
    public Persons update(Persons entity) {

        Persons findedPerson = personRepository.findById(entity.getId()).get() ;

        findedPerson.setLastName(entity.getLastName());
        findedPerson.setFirstName(entity.getFirstName());
        findedPerson.setFatherName(entity.getFatherName());
        findedPerson.setCreateDate(entity.getCreateDate());
        findedPerson.setLabel(entity.getLabel());


        return em.merge(findedPerson) ;
    }

    @Override
    public Persons findById(Object id) {
         return personRepository.findById((String)id).get() ;
    }


}
