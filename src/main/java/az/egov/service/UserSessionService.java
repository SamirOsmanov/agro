package az.egov.service;

import az.egov.entity.UserSession;
import az.egov.repository.UserSessionRepository;
import az.egov.service.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 13.09.2018.
 */

public interface UserSessionService extends CRUDService<UserSession> {
    UserSession findBySessionIdAndStatusId(String value , Integer statusId) ;
    void        destroySession(String sessionId) ;
}
