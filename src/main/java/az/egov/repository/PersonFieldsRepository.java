package az.egov.repository;

import az.egov.entity.PersonFields;
import az.egov.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 14.09.2018.
 */
@Repository
public interface PersonFieldsRepository extends JpaRepository<PersonFields,Integer> {

   List<PersonFields> findByPerson(Persons person) ;
}
