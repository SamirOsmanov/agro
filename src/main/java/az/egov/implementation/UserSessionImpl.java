package az.egov.implementation;


import static az.egov.utility.helper.OperationStatus.* ;
import az.egov.entity.UserSession;
import az.egov.repository.UserSessionRepository;
import az.egov.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 13.09.2018.
 */
@Service
public class UserSessionImpl implements UserSessionService {

    @Autowired
    UserSessionRepository userSessionRepository ;



    @Override
    public UserSession save(UserSession entity) {
        return userSessionRepository.save(entity);
    }

    @Override
    public UserSession findBySessionIdAndStatusId(String value, Integer statusId) {
        return userSessionRepository.findBySessionIdAndStatusId(value,statusId);
    }

    @Override
    public void destroySession(String sessionId) {
        UserSession session = userSessionRepository.findBySessionId(sessionId);

        session.setStatusId(UPDATE_USER_STATUS.getStatusId());
        userSessionRepository.saveAndFlush(session) ;
    }
}
