package az.egov.implementation;

import az.egov.entity.AppealTypes;
import az.egov.entity.RequestTypes;
import az.egov.repository.AppealTypesRepository;
import az.egov.service.AppealTypeService;
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
 * Created by admin on 11.09.2018.
 */
@Service
public class AppealTypesImpl implements AppealTypeService {


    @Autowired
    AppealTypesRepository appealTypesRepository ;

    @PersistenceContext
    private EntityManager em ;


    @Override
    public List<AppealTypes> extendedSearch(Integer statusId,
                                            Integer requestTypeId,
                                            Integer parentId) {

        Session session = em.unwrap(Session.class) ;

        Criteria criteria = session.createCriteria(AppealTypes.class)
                .createAlias("requestTypes","r");


        PropertyValidator.isNull(criteria,"statusId",statusId);
        PropertyValidator.isNull(criteria,"r.id",requestTypeId);

        if(parentId != null)
          criteria.add(Restrictions.eq("parentId",parentId)) ;
        else
          criteria.add(Restrictions.isNull("parentId")) ;



        return criteria.list() ;

    }
}
