package az.egov.implementation;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.repository.PersonFieldsRepository;
import az.egov.service.PersonFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by admin on 14.09.2018.
 */
@Service
public class PersonFieldsImpl implements PersonFieldService {

    @Autowired
    private PersonFieldsRepository personFieldRepository ;

    @PersistenceContext
    EntityManager em ;

    @Override
    public List<PersonFields> getPersonFieldsList(int offset, int fetch) {
        return em.createQuery("from PersonFields")
                  .setFirstResult(offset -1)
                 .setMaxResults(fetch)
                  .getResultList() ;
    }

    @Override
    public List<PersonFields> findByPersonId(Persons person) {
        return personFieldRepository.findByPerson(person);
    }

    @Override
    public PersonFields save(PersonFields entity) {
        return personFieldRepository.save(entity) ;
    }

    @Override
    public PersonFields update(PersonFields entity) {
        return personFieldRepository.saveAndFlush(entity);
    }
}
