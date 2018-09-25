package az.egov.service.common;

import az.egov.response.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 04.09.2018.
 */
@Transactional
public interface CRUDService<T> {

    default public T  save(T entity) {return null ; } ;
    default public T  update(T entity) {return null ; } ;

    default public T  findById(Object id){
        return null ;
    } ;



    default public List<T> list(Integer offset ,
                                Integer fetch){
        return null ;
    } ;

    default public T  deleteById(Object id){
        return null ;
    } ;
}
