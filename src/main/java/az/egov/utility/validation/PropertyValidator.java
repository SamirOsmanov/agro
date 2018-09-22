package az.egov.utility.validation;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by admin on 04.09.2018.
 */
public class PropertyValidator {

    public static void isNull(Criteria criteria , String property , Object value )
    {
        if(value != null)
          criteria.add(Restrictions.eq(property,value)) ;
    }

    public static void like(Criteria criteria , String property , Object value )
    {
        if(value != null)
            criteria.add(Restrictions.like(property,value)) ;
    }
}
