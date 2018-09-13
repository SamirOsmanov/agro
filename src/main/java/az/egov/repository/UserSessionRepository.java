package az.egov.repository;

import az.egov.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 13.09.2018.
 */
@Repository
@Transactional
public interface UserSessionRepository extends JpaRepository<UserSession,Integer> {
}
