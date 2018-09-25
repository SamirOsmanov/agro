package az.egov.implementation;

import az.egov.entity.Positions;
import az.egov.repository.PositionsRepository;
import az.egov.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 25.09.2018.
 */
@Service
public class PositionsImpl implements PositionService {

    @Autowired
    private PositionsRepository positionRepository ;

    @Override
    public List<Positions> findAll() {
        return positionRepository.findAll();
    }
}
