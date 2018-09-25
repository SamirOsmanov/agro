package az.egov.service;

import az.egov.entity.Positions;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */
public interface PositionService extends CRUDService<Positions> {

     List<Positions> findAll()  ;
}
