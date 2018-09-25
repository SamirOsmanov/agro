package az.egov.repository;

import az.egov.entity.ActivityAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 25.09.2018.
 */
@Repository
@Transactional
public interface ActivityAreasRepository extends JpaRepository<ActivityAreas,Integer> {
}
