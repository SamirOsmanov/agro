package az.egov.implementation;

import az.egov.entity.News;
import az.egov.repository.NewsRepository;
import az.egov.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by admin on 10.09.2018.
 */
@Service
public class NewsImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository ;

    @PersistenceContext
    EntityManager em ;

    @Override
    public News save(News entity) {
        return newsRepository.save(entity) ;
    }

    @Override
    public News findById(Object id) {
        Integer newsId = (Integer)id ;
        return newsRepository.findById(newsId).get() ;
    }

    @Override
    public List<News> list(Integer offset, Integer fetch) {

        
        return  em.createQuery("from News")
                  .setFirstResult(offset)
                  .setMaxResults(fetch)
                  .getResultList() ;
    }

    @Override
    public News update(News entity) {
        return null;
    }
}
