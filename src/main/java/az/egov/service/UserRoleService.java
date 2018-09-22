package az.egov.service;

import az.egov.entity.UserRoles;
import az.egov.entity.Users;

/**
 * Created by admin on 17.09.2018.
 */

public interface UserRoleService {
    UserRoles findByUser(Users user) ;
}
