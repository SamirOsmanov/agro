package az.egov.aspect;


import static az.egov.utility.helper.OperationStatus.* ;
import az.egov.entity.UserSession;
import az.egov.implementation.UserSessionImpl;
import az.egov.repository.UserSessionRepository;
import az.egov.response.ResponseEntity;
import az.egov.service.PersonFieldService;
import az.egov.service.UserSessionService;
import az.egov.utility.exception.InvalidSessionException;
import az.egov.utility.exception.SIDNotFoundException;
import az.egov.utility.exception.SessionTimeoutException;
import az.egov.utility.exception.TokenNotFoundException;
import az.egov.utility.helper.Messages;
import az.egov.utility.helper.OperationStatus;
import az.egov.utility.helper.Values;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 03.09.2018.
 */

@Aspect
//@Configuration
public class CheckTokenAspect {

    @Autowired
    UserSessionService userSessionService ;



    @Value("${agro.user.token}")
    private String TOKEN_VALUE ;

    /*@Pointcut("execution(* az.egov.controller.*.*(..))" +
            "  && !execution(* az.egov.controller.UserController.*(..))" +
            "  && !execution(* az.egov.controller.PersonController.verifyPerson(..))")
    public void exludedMethods() {}*/


    @Pointcut("execution(* az.egov.controller.AppealTypesController.*(..))")
    public void appealTypeAspect() {} ;


    @Pointcut("execution(* az.egov.controller.DictionaryController.*(..))")
    public void dictionaryAspect() {} ;


    @Pointcut("execution(* az.egov.controller.MemberShipController.*(..))")
    public void membershipAspect() {} ;


    @Pointcut("execution(* az.egov.controller.NewsController.*(..))")
    public void newseAspect() {} ;


    @Pointcut("execution(* az.egov.controller.NotificationController.*(..))")
    public void notificationAspect() {} ;


    @Pointcut("execution(* az.egov.controller.PersonAppealsController.*(..))")
    public void personAppealAspect() {} ;



    @Before("appealTypeAspect()")
    public void checkAppealAspect()
    {
        checkRequestHeaderParams();
    }


    @Before("dictionaryAspect()")
    public void checkDictionaryAspect()
    {
        checkRequestHeaderParams();
    }

    @Before("membershipAspect()")
    public void checkMembershipAspect()
    {
        checkRequestHeaderParams();
    }

    @Before("newseAspect()")
    public void checkNewseAspect()
    {
        checkRequestHeaderParams();
    }

    @Before("(notificationAspect())")
    public void checkNotificationAspect()
    {
        checkRequestHeaderParams();
    }
    @Before("personAppealAspect()")
    public void checkPersonAppealAspect()
    {
        checkRequestHeaderParams();
    }


    public void checkRequestHeaderParams()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userToken = request.getHeader("token") ;

        String sessionID = request.getHeader("SID") ;


        if(userToken == null)
        {
           throw new TokenNotFoundException(Messages.TOKEN_NOT_FOUND.getMessageContent()) ;
        } else if (!userToken.equals(TOKEN_VALUE)) {
           throw new TokenNotFoundException(Messages.INVALID_TOKEN.getMessageContent()) ;
        }


        if(sessionID == null)
        {
           throw new SIDNotFoundException(Messages.SID_NOT_FOUND.getMessageContent()) ;
        }
        else
        {
            UserSession userSession = userSessionService.findBySessionIdAndStatusId(sessionID, ACTIVE_USER_STATUS.getStatusId());

            if(userSession == null)
            {
                throw new InvalidSessionException(Messages.INVALID_SID.getMessageContent()) ;
            }

            String sourceType = request.getHeader("source") ;

            if(sourceType != null )
            {
                if(sourceType.toUpperCase().equals(Values.SOURCE.name()))
                {
                    userSession.setIsMobile(OperationStatus.IS_MOBILE.getStatusId());
                    userSessionService.save(userSession);
                }
            }
            else {
                long activityTime = userSession.getLastActivity().getTime();
                long currentTime = new Date().getTime();

                long activityDuration = TimeUnit.MILLISECONDS
                        .toMinutes(currentTime - activityTime);

                if (activityDuration > 30) {
                    userSession.setStatusId(OperationStatus.SESSION_EXPIRED.getStatusId());
                    userSessionService.save(userSession);
                    throw new SessionTimeoutException(Messages.SESSION_TIMEOUT.getMessageContent());
                } else {
                    userSession.setLastActivity(new Date());
                    userSessionService.save(userSession);
                }
            }



        }


    }



}
