package az.egov.repository;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 14.09.2018.
 */
@Repository
@Transactional
public interface PersonFieldsRepository extends JpaRepository<PersonFields,Integer> {

   List<PersonFields> findByPersonAndStatusId(Persons person,Integer statusId) ;
   List<PersonFields> findByPerson(Persons person) ;

   @Query("select count(pf) from PersonFields as pf where pf.statusId != 3 ")
   Long totalCount() ;
}
