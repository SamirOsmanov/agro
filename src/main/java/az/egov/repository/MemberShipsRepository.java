package az.egov.repository;

import az.egov.entity.MemberShips;
import az.egov.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 24.09.2018.
 */

@Repository
@Transactional
public interface MemberShipsRepository extends JpaRepository<MemberShips,Integer> {

    Integer countByStatusNot(Status status) ;

    MemberShips findByIdAndStatusNot(Integer id , Status status) ;
}
