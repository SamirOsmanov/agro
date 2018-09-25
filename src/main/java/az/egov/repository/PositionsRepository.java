package az.egov.repository;

import az.egov.entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 25.09.2018.
 */
@Repository
@Transactional
public interface PositionsRepository extends JpaRepository<Positions,Integer> {
}
