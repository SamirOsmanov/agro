package az.egov.utility.helper;

import az.egov.entity.Persons;
import az.egov.response.ResponseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 05.09.2018.
 */
public class AroundAdvice<T> {


    public ResponseEntity<T> responseEntity ;

    public ResponseEntity<T> generateResponse(ProceedingJoinPoint joinPoint)
    {
        try
        {


            T responseData = (T) joinPoint.proceed();

            if(responseData != null)
            {
                responseEntity =   new ResponseEntity(HttpStatus.OK.value() ,
                                                responseData,
                                                Messages.SUCCES_CONTENT.getMessageContent(),
                                                new Date()) ;

            }
            else
            {
                responseEntity = new ResponseEntity(HttpStatus.OK.value() ,
                                             null ,
                                              Messages.NODATA_CONTENT.getMessageContent(),
                                              new Date()) ;
            }
        }
        catch (Throwable throwable)
        {
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value() ,
                                        null ,
                                        Messages.FAILED_CONTENT.getMessageContent() +
                                            throwable.getMessage(),
                                            new Date()) ;
        }
        finally {
            return responseEntity ;
        }
    }
}
