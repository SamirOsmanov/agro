package az.egov.repository;

import az.egov.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 10.09.2018.
 */
@Transactional
@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {
}
