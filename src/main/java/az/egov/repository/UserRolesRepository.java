package az.egov.repository;

import az.egov.entity.UserRoles;
import az.egov.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 17.09.2018.
 */

@Repository
@Transactional
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {

    UserRoles findByUser(Users user) ;
}
