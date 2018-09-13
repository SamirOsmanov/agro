package az.egov.service;

import az.egov.entity.AppealTypes;
import az.egov.entity.RequestTypes;
import az.egov.service.common.CRUDService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 11.09.2018.
 */

public interface AppealTypeService extends CRUDService<AppealTypes> {

    public List<AppealTypes> extendedSearch(Integer statusId,
                                            Integer requestTypeId);
}
