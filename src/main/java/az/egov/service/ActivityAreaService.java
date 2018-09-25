package az.egov.service;

import az.egov.entity.ActivityAreas;
import az.egov.service.common.CRUDService;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */
public interface ActivityAreaService extends CRUDService<ActivityAreas> {

    List<ActivityAreas> findAll() ;
}
