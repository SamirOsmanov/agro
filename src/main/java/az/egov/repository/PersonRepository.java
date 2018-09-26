package az.egov.repository;

import az.egov.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Persons,Object> {

    @Query(value = " SELECT  p.* FROM [KTN-DB].[List].[Persons] p " +
                   " ORDER BY create_date DESC  OFFSET :offset ROWS FETCH NEXT :fetch ROWS ONLY" ,
           nativeQuery = true)
    public List<Persons> getPersonList(@Param("offset") Integer offset ,
                                       @Param("fetch")  Integer fetch) ;

    public Persons findByPin(String pin) ;


    @Modifying
    @Query(value = "INSERT INTO [Rel].[PersonActivityAreas] (person_id,active_area_id,area_id) " +
                   "  VALUES (:personId,:activityId,:areaId)" ,
                   nativeQuery = true)
    public Integer savePersonActivity(@Param("personId")   String personId,
                                      @Param("activityId") Integer activityId,
                                      @Param("areaId") Integer areaId) ;

    @Modifying
    @Query(value = "DELETE FROM [Rel].[PersonActivityAreas] WHERE person_id=:personId",
           nativeQuery = true)
    public void deletePersonActivity(@Param("personId")   String personId) ;


    @Modifying
    @Query(value = "INSERT INTO [Rel].[PersonContacts] (person_id,contact_type_id,contact_info) " +
            " VALUES (:personId,:contactTypeId,:contactInfo)",
    nativeQuery = true)
    public void savePersonContacts(@Param("personId") String personId,
                                   @Param("contactTypeId") Integer contactTypeId,
                                   @Param("contactInfo") String contactInfo) ;


    @Modifying
    @Query(value = "INSERT INTO [Rel].[PersonAddress] (person_id,address_type_id,address) " +
            " VALUES (:personId,:addressTypeId,:address)",
            nativeQuery = true)
    public void savePersonAddress( @Param("personId") String personId,
                                   @Param("addressTypeId") Integer addressTypeId,
                                   @Param("address") String address) ;


}


