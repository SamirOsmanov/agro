package az.egov.service;

import az.egov.entity.MemberShips;
import az.egov.entity.Status;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 24.09.2018.
 */

public interface MemberShipService extends CRUDService<MemberShips> {

   List<MemberShips> extendedSearch(String name,
                                    Integer memberShipType,
                                    Integer activityArea
                                    ) ;

    List<MemberShips> getMemberShipList(Integer offset,Integer fetch) ;

    Integer totalCount(Status status) ;
}
