package az.egov.aspect;

import az.egov.utility.exception.TokenNotFoundException;
import az.egov.utility.helper.Messages;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 03.09.2018.
 */

@Aspect
@Configuration
public class CheckTokenAspect {

    @Pointcut("execution(* az.egov.controller.*.*(..))")
    public void requestHeader() {}

    @Before("requestHeader()")
    public void checkRequestHeaderParams()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token") ;

        if(token == null)
        {
           throw new TokenNotFoundException(Messages.TOKEN_NOT_FOUND.getMessageContent()) ;
        }
    }


}
