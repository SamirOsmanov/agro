package az.egov.implementation;

import az.egov.entity.UserRoles;
import az.egov.entity.Users;
import az.egov.repository.UserRolesRepository;
import az.egov.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 17.09.2018.
 */
@Service
public class UserRolesImpl implements UserRoleService {

    @Autowired
    private UserRolesRepository userRolesRepository ;

    @Override
    public UserRoles findByUser(Users user) {
        return userRolesRepository.findByUser(user);
    }
}
