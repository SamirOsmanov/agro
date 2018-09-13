package az.egov.repository;

import az.egov.entity.AppealTypes;
import az.egov.entity.RequestTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 11.09.2018.
 */
@Repository
@Transactional
public interface AppealTypesRepository extends JpaRepository<AppealTypes,Integer> {

    List<AppealTypes> findByStatusId(Integer statusId) ;
}
