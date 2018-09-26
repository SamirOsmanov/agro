package az.egov.implementation;

import az.egov.entity.AddressTypes;
import az.egov.repository.AddressTypesRepository;
import az.egov.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 26.09.2018.
 */
@Service
public class AddressTypesImpl implements AddressTypeService {

    @Autowired
    private AddressTypesRepository addressTypesRepository ;

    @Override
    public List<AddressTypes> findAll() {
        return addressTypesRepository.findAll();
    }
}
