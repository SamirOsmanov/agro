package az.egov.service;

import az.egov.entity.Users;
import az.egov.model.PersonModel;
import az.egov.service.common.CRUDService;

/**
 * Created by admin on 12.09.2018.
 */
public interface UserService extends CRUDService<Users> {

    public Users find(String username,String password) ;

    public Users  save(Users entity,PersonModel personModel) ;
}
