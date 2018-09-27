package az.egov.repository;

import az.egov.entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

@Repository
public interface AreasRepository extends JpaRepository<Areas,Integer> {

    List<Areas> findByAreaTypeIdAndParentIdNot(Integer areaTypeId,Integer parentId) ;
}
