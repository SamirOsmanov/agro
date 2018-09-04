package az.egov.repository;

import az.egov.entity.PersonAppeals;
import az.egov.entity.Persons;
import az.egov.model.PersonAppealsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@Repository
public interface PersonAppealsRepository extends CrudRepository<PersonAppeals, BigInteger> {

    @Query(value = " SELECT a.id, a.create_date, a.person_id,b.first_name," +
                   "        b.last_name,b.father_name, ISNULL(a.message, '' ) as message, " +
                   "        d.id as Request_ID,d.name as Request_Name,c.id as Appeal_ID," +
                   "        c.name as Appeal_Name FROM [KTN-DB].[Rel].[PersonAppeals] a " +
                   " LEFT JOIN [KTN-DB].[List].[Persons] b ON a.person_id = b.id " +
                   " LEFT JOIN [KTN-DB].[List].[AppealTypes] c  ON a.appeal_type_id = c.id " +
                   " LEFT JOIN [KTN-DB].[List].[RequestTypes] d ON c.request_type_id = d.id " +
                   " ORDER BY c.id DESC OFFSET :offset ROWS FETCH NEXT :fetch ROWS ONLY" ,
            nativeQuery = true)
    public List<Object[]> getPersonAppeals(@Param("offset") Integer offset ,
                                                     @Param("fetch")  Integer fetch) ;


}
