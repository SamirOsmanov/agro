package az.egov.service.common;

import az.egov.response.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 04.09.2018.
 */
@Transactional
public interface CRUDService<T> {

    public void  save(T entity) ;
    public void  update(T entity) ;
    public T     findById(Object id) ;
}
