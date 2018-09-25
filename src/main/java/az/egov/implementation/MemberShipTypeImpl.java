package az.egov.implementation;

import az.egov.entity.MemberShipTypes;
import az.egov.repository.MemberShipTypesRepository;
import az.egov.service.MembershipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */
@Service
public class MemberShipTypeImpl implements MembershipTypeService
{

    @Autowired
    MemberShipTypesRepository memberShipTypeRepository ;

    @Override
    public List<MemberShipTypes> findAll() {
        return memberShipTypeRepository.findAll() ;
    }
}
