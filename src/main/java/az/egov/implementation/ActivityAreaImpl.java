package az.egov.implementation;

import az.egov.entity.ActivityAreas;
import az.egov.repository.ActivityAreasRepository;
import az.egov.service.ActivityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */

@Service
public class ActivityAreaImpl implements ActivityAreaService {

    @Autowired
    private ActivityAreasRepository activityAreaRepository ;

    @Override
    public List<ActivityAreas> findAll() {
        return activityAreaRepository.findAll();
    }
}
