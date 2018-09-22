package az.egov.implementation;

import az.egov.entity.News;
import az.egov.entity.PersonAppeals;
import az.egov.repository.NewsRepository;
import az.egov.service.NewsService;
import az.egov.utility.helper.OperationStatus;
import az.egov.utility.validation.PropertyValidator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
       return (News) em.createQuery("from News as n where n.statusId != :statusId and id = :id ")
         .setParameter("id",id)
         .setParameter("statusId",OperationStatus.DELETE_STATUS.getStatusId())
         .getResultList().get(0);
    }

    @Override
    public List<News> list(Integer offset, Integer fetch) {

        
        return  em.createQuery("from News where statusId = :statusId")
                  .setParameter("statusId", OperationStatus.INSERT_STATUS.getStatusId())
                  .setFirstResult(offset)
                  .setMaxResults(fetch)
                  .getResultList() ;
    }



    @Override
    public News update(News entity) {

        return em.merge(entity);
    }



    @Override
    public News findByIdAndStatusId(Integer id, Integer statusId) {
        return newsRepository.findByIdAndStatusId(id,statusId);
    }

    @Override
    public Long totalCount() {
        return newsRepository.totalCount() ;
    }

    @Override
    public List<News> findByNewsAndStatusId(News news, Integer statusId) {
        Session session = em.unwrap(Session.class) ;
        Criteria criteria = session.createCriteria(News.class) ;
        PropertyValidator.isNull(criteria,"id",news.getId());
        PropertyValidator.like(criteria,"title",news.getTitle());
        PropertyValidator.like(criteria,"description",news.getDescription());
        PropertyValidator.like(criteria,"content",news.getContent());

        criteria.add(Restrictions.ne("statusId",statusId)) ;

        return criteria.list() ;
    }
}
