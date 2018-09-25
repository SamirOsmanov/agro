package az.egov.repository;

import az.egov.entity.MemberShips;
import az.egov.entity.PersonMemberships;
import az.egov.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 24.09.2018.
 */
@Repository
@Transactional
public interface PersonMembershipRepository extends JpaRepository<PersonMemberships,Integer> {

    PersonMemberships findByPersonAndMemberShips(Persons person , MemberShips memberShip) ;
}
