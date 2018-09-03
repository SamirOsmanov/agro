package az.egov.implementation;

import az.egov.entity.PersonAppeals;
import az.egov.model.PersonAppealsModel;
import az.egov.repository.PersonAppealsRepository;
import az.egov.service.PersonAppealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 03.09.2018.
 */
@Service
public class PersonAppealsImpl implements PersonAppealsService {

    @Autowired
    PersonAppealsRepository appealsRepository ;

    @Override
    public List<PersonAppealsModel> getPersonAppeals(int offset, int fetch) {
        return appealsRepository.getPersonAppeals(offset,fetch)
                                .stream()
                                .map(temp -> {
                                     return new PersonAppealsModel(  (BigInteger) temp[0],
                                                                     (Date)       temp[1],
                                                                     (String)     temp[2],
                                                                     (String)     temp[3],
                                                                     (String)     temp[4],
                                                                     (String)     temp[5],
                                                                     (String)     temp[6],
                                                                     (Integer)    temp[7],
                                                                     (String)     temp[8],
                                                                     (Integer)    temp[9],
                                                                     (String)     temp[10]

                                                                   ) ;
                                }).collect(Collectors.toList()) ;
    }
}
