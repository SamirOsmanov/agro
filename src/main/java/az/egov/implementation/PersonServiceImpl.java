package az.egov.implementation;

import az.egov.entity.PersonAppeals;
import az.egov.entity.Persons;
import az.egov.repository.Log4MongoRepository;
import az.egov.repository.PersonRepository;
import az.egov.response.ResponseEntity;
import az.egov.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository ;

    @Autowired
    private Log4MongoRepository mongoRepository ;

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
         String personId = (String)id ;
         return personRepository.findById(id).get() ;
    }


}
