package az.egov.implementation;

import az.egov.entity.MemberShips;
import az.egov.entity.PersonMemberships;
import az.egov.entity.Persons;
import az.egov.repository.PersonMembershipRepository;
import az.egov.service.PersonMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 24.09.2018.
 */
@Service
public class PersonMembershipImpl implements PersonMembershipService {

    @Autowired
    private PersonMembershipRepository personMembershipRepository ;

    @Override
    public PersonMemberships save(PersonMemberships entity) {
        return personMembershipRepository.save(entity);
    }

    @Override
    public PersonMemberships findByPersonAndMemberShips(Persons person, MemberShips memberShip) {
        return personMembershipRepository.findByPersonAndMemberShips(person,memberShip);
    }
}
