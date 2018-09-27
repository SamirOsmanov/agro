package az.egov.repository;

import az.egov.entity.ActivityAreas;
import az.egov.entity.Areas;
import az.egov.entity.PersonActivityAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

@Repository
@Transactional
public interface PersonActivityAreasRepository extends JpaRepository<PersonActivityAreas,Integer> {

    @Query
    List<PersonActivityAreas> findDistinctByAreaId(Areas area) ;
}
