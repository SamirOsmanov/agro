package az.egov.implementation;

import az.egov.entity.Persons;
import az.egov.repository.PersonRepository;
import az.egov.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 03.09.2018.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository ;

    @Override
    public List<Persons> getPersonList(int offset, int fetch) {
        return  personRepository.getPersonList(offset,fetch) ;
    }

}
