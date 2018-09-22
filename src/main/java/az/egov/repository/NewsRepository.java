package az.egov.repository;

import az.egov.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 10.09.2018.
 */
@Transactional
@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {

    News findByIdAndStatusId(Integer id , Integer statusId) ;

    @Query("SELECT n FROM News as n where n = :p_news")
    List<News> getNewsList(@Param("p_news") News p_news) ;

    @Query("select count(n) from News as n where n.statusId != 3")
    public Long totalCount() ;

    //List<News> findByNewsAndStatusIdNot(News news , Integer StatusId) ;
}
