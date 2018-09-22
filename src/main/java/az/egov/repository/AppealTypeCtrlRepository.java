package az.egov.repository;

import az.egov.entity.AppealTypeCtrl;
import az.egov.entity.AppealTypes;
import az.egov.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 17.09.2018.
 */
@Repository
@Transactional
public interface AppealTypeCtrlRepository extends JpaRepository<AppealTypeCtrl,Integer> {

    AppealTypeCtrl findByUser(Users user) ;
}
