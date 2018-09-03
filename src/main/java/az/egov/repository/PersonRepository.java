package az.egov.repository;

import az.egov.entity.Persons;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@Repository
public interface PersonRepository extends CrudRepository<Persons,Object> {

    @Query(value = " SELECT  p.* FROM [KTN-DB].[List].[Persons] p " +
                   " ORDER BY create_date DESC  OFFSET :offset ROWS FETCH NEXT :fetch ROWS ONLY" ,
           nativeQuery = true)
    public List<Persons> getPersonList(@Param("offset") Integer offset ,
                                       @Param("fetch")  Integer fetch) ;


}


