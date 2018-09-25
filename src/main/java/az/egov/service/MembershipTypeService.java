package az.egov.service;

import az.egov.entity.MemberShipTypes;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */
public interface MembershipTypeService extends CRUDService<MemberShipTypes> {

    List<MemberShipTypes> findAll() ;
}
