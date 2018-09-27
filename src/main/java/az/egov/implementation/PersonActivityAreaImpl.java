package az.egov.implementation;

import az.egov.entity.ActivityAreas;
import az.egov.entity.Areas;
import az.egov.entity.PersonActivityAreas;
import az.egov.repository.PersonActivityAreasRepository;
import az.egov.service.PersonActivityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */

@Service
public class PersonActivityAreaImpl implements PersonActivityAreaService {

    @Autowired
    private PersonActivityAreasRepository activityAreasRepository ;

    @Override
    public List<ActivityAreas> findAactivityByAreaId(Areas area) {

        List<ActivityAreas> activityAreasList = new ArrayList<>() ;

        activityAreasRepository.findDistinctByAreaId(area)
                               .forEach(item -> {
                                   activityAreasList.add(item.getActivityId());
                               });

        return activityAreasList ;
    }
}
