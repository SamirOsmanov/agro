package az.egov.service;

import az.egov.entity.MemberShips;
import az.egov.entity.PersonMemberships;
import az.egov.entity.Persons;
import az.egov.service.common.CRUDService;

/**
 * Created by admin on 24.09.2018.
 */
public interface PersonMembershipService extends CRUDService<PersonMemberships> {

    PersonMemberships findByPersonAndMemberShips(Persons person , MemberShips memberShip ) ;
}
