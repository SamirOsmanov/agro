package az.egov.service;

import az.egov.entity.ActivityAreas;
import az.egov.entity.Areas;
import az.egov.entity.PersonActivityAreas;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */
public interface PersonActivityAreaService {

    List<ActivityAreas> findAactivityByAreaId(Areas area) ;
}
