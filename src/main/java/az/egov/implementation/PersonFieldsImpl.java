package az.egov.implementation;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import az.egov.repository.PersonFieldsRepository;
import az.egov.service.PersonFieldService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
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
        return em.createQuery("from PersonFields as p where p.statusId != 3 ")
                  .setFirstResult(offset)
                 .setMaxResults(fetch)
                  .getResultList() ;
    }

    @Override
    public PersonFields findById(Object id) {
        return (PersonFields) em.createQuery("from PersonFields as p " +
                                 "where p.statusId != :statusId and p.id=:id")
                             .setParameter("statusId", OperationStatus.DELETE_STATUS.getStatusId())
                             .setParameter("id", id)
                             .getResultList().get(0);
    }

    @Override
    public List<PersonFields> findByPersonAndStatusId(Persons person,Integer statusId) {
         return personFieldRepository.findByPersonAndStatusId(person,statusId);
    }

    @Override
    public Long totalCount() {
        return personFieldRepository.totalCount() ;
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
