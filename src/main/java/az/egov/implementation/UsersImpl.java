package az.egov.implementation;

import az.egov.entity.Persons;
import az.egov.entity.Users;
import az.egov.model.PersonModel;
import az.egov.repository.UserRepository;
import az.egov.service.PersonService;
import az.egov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by admin on 12.09.2018.
 */
@Service
public class UsersImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

    @Autowired
    PersonService personService  ;

    @Override
    public Users  save(Users entity,PersonModel personModel) {

        String  pin = entity.getPin() ;
        Persons person = personService.findByPin(pin);

        if(person != null)
        {
            entity.setPerson(person);
        }
        else
        {
            person = new Persons() ;
            person.setPin(pin);
            person.setFirstName(personModel.getFirstName());
            person.setLastName(personModel.getLastName());
            person.setFatherName(personModel.getMiddleName());
            person.setCreateDate(new Date());


            entity.setPerson(person);
        }
        return userRepository.save(entity) ;
    } ;

    @Override
    public Users find(String username,String password)
    {
        return userRepository.findByUserNameAndPassword(username,password) ;
    }

}
