package az.egov.implementation;

import az.egov.entity.MemberShips;
import az.egov.entity.PersonAppeals;
import az.egov.entity.Status;
import az.egov.repository.MemberShipsRepository;
import az.egov.service.MemberShipService;
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
 * Created by admin on 24.09.2018.
 */
@Service
public class MemberShipsImpl implements MemberShipService {

    @Autowired
    private MemberShipsRepository memberShipsRepository ;

    @PersistenceContext
    EntityManager em ;

    @Override
    public MemberShips save(MemberShips entity) {
        return memberShipsRepository.save(entity);
    }


    @Override
    public List<MemberShips> extendedSearch(String name,
                                            Integer memberShipType,
                                            Integer activityArea) {

        Session session = em.unwrap(Session.class) ;

        Criteria criteria = session.createCriteria(MemberShips.class)
                                    .createAlias("memberShipTypes","mst")
                                    .createAlias("activityAreas","a")
                                    .createAlias("status","s");

        PropertyValidator.isNull(criteria,"name",name);
        PropertyValidator.isNull(criteria,"mst.id",memberShipType);
        PropertyValidator.isNull(criteria,"a.id",activityArea);

        criteria.add(Restrictions.ne("s.id", OperationStatus.DELETE_STATUS.getStatusId()));


        return criteria.list() ;
    }

    @Override
    public List<MemberShips> getMemberShipList(Integer offset, Integer fetch) {
       return    em.createQuery(" from MemberShips as m where m.status.id != 3 ")
                   .setFirstResult(offset)
                   .setMaxResults(fetch)
                   .getResultList() ;
    }

    @Override
    public Integer totalCount(Status status) {
        return memberShipsRepository.countByStatusNot(status);
    }

    @Override
    public MemberShips update(MemberShips entity) {
        return memberShipsRepository.saveAndFlush(entity);
    }

    @Override
    public MemberShips findById(Object id) {

        Status status = new Status(OperationStatus.DELETE_STATUS.getStatusId()) ;

        return memberShipsRepository.findByIdAndStatusNot((Integer) id,status) ;
    }
}
