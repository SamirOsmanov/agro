package az.egov.aspect;

import az.egov.entity.Persons;
import az.egov.repository.Log4MongoRepository;
import az.egov.repository.PersonRepository;
import az.egov.response.ResponseEntity;
import az.egov.utility.helper.AroundAdvice;
import az.egov.utility.helper.Messages;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 05.09.2018.
 */
@Aspect
@Configuration
public class PersonAroundAspect {

    @Autowired
    private Log4MongoRepository mongoRepository ;


    @Autowired
    PersonRepository personRepository ;


    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* az.egov.controller.PersonController.getPersonList(..))")
    public void personList() {}

    @Pointcut("execution(* az.egov.controller.PersonController.findById(..))")
    public void findPersonById() {}


    @Pointcut("execution(* az.egov.controller.PersonController.savePerson(..))")
    public void savePerson() {}

    @Pointcut("execution(* az.egov.controller.PersonController.updatePerson(..))")
    public void updatePerson() {}


    // ------------------------------------------------------------------------------

    @Around("personList()")
    public ResponseEntity<List<Persons>>  personListAround(ProceedingJoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : {offset : %s , fetch : %s  } ############ \n", args[0] , args[1]));

        AroundAdvice<List<Persons>> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<List<Persons>> listResponseEntity = aroundAdvice.generateResponse(joinPoint);

        if(listResponseEntity != null) {
            mongoRepository.save(listResponseEntity);

            LOGGER.info(String.format("\t\t\t ---------------- RESPONSE DATA :  ---------------- \n"));

            for (Persons person : listResponseEntity.getData()) {
                LOGGER.info("\t\t\t\t\t " + person.toString());
            }

            listResponseEntity.setTotalCount(personRepository.count());
        }

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");

        return listResponseEntity ;
    }

    @Around("findPersonById()")
    public ResponseEntity<Persons> findPersonAround(ProceedingJoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : {ID : %s  }  ############\n", args[0]));


        AroundAdvice<Persons> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<Persons> responseEntity = aroundAdvice.generateResponse(joinPoint);
        if(responseEntity != null)
          mongoRepository.save(responseEntity) ;

        LOGGER.info("\t\t\t ---------------- RESPONSE DATA : ---------------- \n");
        LOGGER.info("\t\t\t\t\t " + responseEntity.getData().toString());
        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");


        return responseEntity ;
    }

    @Around("savePerson()")
    public ResponseEntity<Persons>  personSaveAround(ProceedingJoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : %s  ############\n", ((Persons)args[0]).toString() ));

        AroundAdvice<Persons> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<Persons> responseEntity = aroundAdvice.generateResponse(joinPoint);

        if(responseEntity!= null)
         mongoRepository.save(responseEntity) ;

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");

        return responseEntity ;
    }

    @Around("updatePerson()")
    public ResponseEntity<Persons>  personUpdateAround(ProceedingJoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : %s ############  \n", ((Persons)args[0]).toString() ));


        AroundAdvice<Persons> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<Persons> responseEntity = aroundAdvice.generateResponse(joinPoint);

        if(responseEntity!= null)
         mongoRepository.save(responseEntity) ;

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");


        return  responseEntity ;
    }




}
