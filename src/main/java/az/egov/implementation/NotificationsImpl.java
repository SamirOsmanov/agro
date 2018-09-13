package az.egov.implementation;

import az.egov.entity.Notifications;
import az.egov.repository.NotificationsRepository;
import az.egov.service.NotificationsService;
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
         return  em.createQuery("from Notifications")
                   .setFirstResult(offset)
                   .setMaxResults(fetch)
                   .getResultList() ;
    }

    @Override
    public Notifications findById(Object id) {
        BigInteger notificationId = (BigInteger)id ;
        return notificationsRepository.findById(notificationId).get() ;
    }

    @Override
    public Notifications update(Notifications entity) {
        return null;
    }
}
