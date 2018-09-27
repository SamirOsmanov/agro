package az.egov.implementation;

import az.egov.entity.Areas;
import az.egov.repository.AreasRepository;
import az.egov.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 27.09.2018.
 */
@Service
public class AreaImpl implements AreaService {

    @Autowired
    private AreasRepository areasRepository ;

    @Override
    public List<Areas> findAll(Integer areaTypeId,Integer parentId) {
        return areasRepository.findByAreaTypeIdAndParentIdNot(areaTypeId,parentId);
    }
}
