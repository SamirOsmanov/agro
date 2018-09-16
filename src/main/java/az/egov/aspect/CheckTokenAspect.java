package az.egov.aspect;

import az.egov.response.ResponseEntity;
import az.egov.utility.exception.TokenNotFoundException;
import az.egov.utility.helper.Messages;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by admin on 03.09.2018.
 */

@Aspect
@Configuration
public class CheckTokenAspect {

    @Value("${agro.user.token}")
    private String TOKEN_VALUE ;

    @Pointcut("execution(* az.egov.controller.*.*(..))")
    public void http() {}

    @Before("http()")
    public void checkRequestHeaderParams()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userToken = request.getHeader("token") ;

        if(userToken == null)
        {
           throw new TokenNotFoundException(Messages.TOKEN_NOT_FOUND.getMessageContent()) ;
        } else if (!userToken.equals(TOKEN_VALUE)) {
           throw new TokenNotFoundException(Messages.INVALID_TOKEN.getMessageContent()) ;
        }
    }



}
