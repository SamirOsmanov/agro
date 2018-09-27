package az.egov.repository;

import az.egov.entity.NotificationPriorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 27.09.2018.
 */
@Repository
@Transactional
public interface NotificationPrioritiesRepository extends JpaRepository<NotificationPriorities,Integer> {
}
