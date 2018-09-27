package az.egov.implementation;

import az.egov.entity.Notifications;
import az.egov.repository.NotificationsRepository;
import az.egov.service.NotificationsService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by admin on 10.09.2018.
 */
@Service
@Transactional
public class NotificationsImpl implements NotificationsService {

    @Autowired
    NotificationsRepository notificationsRepository ;

    @PersistenceContext
    EntityManager em ;


    @Override
    public Notifications save(Notifications entity) {

        return notificationsRepository.save(entity);
    }

    @Override
    public List<Notifications> list(Integer offset, Integer fetch) {
         return  em.createQuery("from Notifications as n") //  where n.status.id != 3
                   .setFirstResult(offset)
                   .setMaxResults(fetch)
                   .getResultList() ;
    }

    @Override
    public Notifications findById(Object id) {

       return (Notifications) em.createQuery("from Notifications as n ") // where n.status.id !=:statusId and id=:id
                  .setParameter("id",id)
                  .setParameter("statusId", OperationStatus.DELETE_STATUS.getStatusId())
                  .getResultList().get(0);
    }

    @Override
    public Notifications update(Notifications entity) {
        return null;
    }

    @Override
    public Long totalCount() {
        return notificationsRepository.totalCount();
    }
}
