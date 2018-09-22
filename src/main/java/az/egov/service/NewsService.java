package az.egov.service;

import az.egov.entity.News;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 10.09.2018.
 */

public interface NewsService extends CRUDService<News> {

    News findByIdAndStatusId(Integer id , Integer statusId) ;
    Long totalCount() ;
    List<News> findByNewsAndStatusId(News news , Integer statusId) ;
}
