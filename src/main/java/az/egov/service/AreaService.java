package az.egov.service;

import az.egov.entity.Areas;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */
public interface AreaService {

    List<Areas> findAll(Integer areaTypeId,Integer parentId) ;
}
