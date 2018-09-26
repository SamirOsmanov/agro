package az.egov.implementation;

import az.egov.entity.ContactTypes;
import az.egov.repository.ContactRepository;
import az.egov.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 26.09.2018.
 */
@Service
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository ;

    @Override
    public List<ContactTypes> findAll() {
        return contactRepository.findAll();
    }
}
