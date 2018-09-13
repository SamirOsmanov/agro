package az.egov.aspect;

import az.egov.entity.PersonAppeals;
import az.egov.entity.Persons;
import az.egov.model.PersonAppealsModel;
import az.egov.repository.Log4MongoRepository;
import az.egov.repository.PersonAppealsRepository;
import az.egov.response.ResponseEntity;
import az.egov.utility.helper.AroundAdvice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by admin on 05.09.2018.
 */

@Aspect
public class PersonAppealAroundAspect {


    @Autowired
    PersonAppealsRepository appealsRepository ;


    @Autowired
    private Log4MongoRepository mongoRepository ;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* az.egov.controller.PersonAppealsController.getPersonAppealsList(..))")
    public void personAppealsList() {}

    @Pointcut("execution(* az.egov.controller.PersonAppealsController.findPersonAppeals(..))")
    public void findPersonAppeals() {}


    @Pointcut("execution(* az.egov.controller.PersonAppealsController.savePersonAppeal(..))")
    public void savePersonAppeals() {}

    @Pointcut("execution(* az.egov.controller.PersonAppealsController.updatePersonAppeal(..))")
    public void updatePersonAppeals() {}


    // ------------------------------------------------------------------------------

    @Around("personAppealsList()")
    public ResponseEntity<List<PersonAppealsModel>> personListAround(ProceedingJoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : {offset : %s , fetch : %s  } ############ \n", args[0] , args[1]));


        AroundAdvice<List<PersonAppealsModel>> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<List<PersonAppealsModel>> listResponseEntity = aroundAdvice.generateResponse(joinPoint);

        if(listResponseEntity != null)
        {
             mongoRepository.save(listResponseEntity) ;
             LOGGER.info(String.format("\t\t\t -------------  RESPONSE DATA : -------------  \n"));
             listResponseEntity.setTotalCount(appealsRepository.count());

             for(PersonAppealsModel appeals : listResponseEntity.getData())
             {
                 LOGGER.info("\t\t\t\t\t " + appeals.toString());
             }
        }

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");

        return listResponseEntity ;
    }

    @Around("findPersonAppeals()")
    public ResponseEntity<List<PersonAppeals>> findPersonAppealsAround(ProceedingJoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : {ID : %s  } ############ \n", args[0]));


        AroundAdvice<List<PersonAppeals>> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<List<PersonAppeals>> responseEntity = aroundAdvice.generateResponse(joinPoint);

        if(responseEntity != null)
        {
            mongoRepository.save(responseEntity) ;
            LOGGER.info("\t\t\t ------------- RESPONSE DATA :  ------------- \n");
            LOGGER.info("\t\t\t\t\t " + responseEntity.getData().toString());
        }

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");

        return responseEntity ;
    }

    @Around("savePersonAppeals()")
    public ResponseEntity<PersonAppeals>  personAppealSaveAround(ProceedingJoinPoint joinPoint)
    {

        PersonAppeals args = (PersonAppeals) joinPoint.getArgs()[0];

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : ############  \n", args.toString()));

        AroundAdvice<PersonAppeals> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<PersonAppeals> personAppealsResponseEntity = aroundAdvice.generateResponse(joinPoint);

        if(personAppealsResponseEntity != null)
        {
            mongoRepository.save(personAppealsResponseEntity) ;
            LOGGER.info("\t\t\t ------------- RESPONSE DATA : ------------- \n ");
            LOGGER.info("\t\t\t\t\t " + personAppealsResponseEntity.getData().toString());
        }

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");

        return personAppealsResponseEntity ;
    }

    @Around("updatePersonAppeals()")
    public ResponseEntity<PersonAppeals>  personAppealAround(ProceedingJoinPoint joinPoint)
    {
        PersonAppeals args = (PersonAppeals) joinPoint.getArgs()[0];

        LOGGER.info(" **********  REQUEST START TIME :  " + LocalDate.now().toString() + " ********** " );
        LOGGER.info(String.format("\t\t\t ############ REQUEST PARAMS : ############  \n", args.toString()));


        AroundAdvice<PersonAppeals> aroundAdvice = new AroundAdvice<>() ;
        ResponseEntity<PersonAppeals> personAppealsResponseEntity = aroundAdvice.generateResponse(joinPoint);

        if(personAppealsResponseEntity != null)
        {
            mongoRepository.save(personAppealsResponseEntity) ;
            LOGGER.info("\t\t\t ------------- RESPONSE DATA :  ------------- \n");
            LOGGER.info("\t\t\t\t\t " + personAppealsResponseEntity.getData().toString());

        }

        LOGGER.info(" **********  RESPONSE      TIME :  " + LocalDate.now().toString() +" ********** ");
        return personAppealsResponseEntity ;
    }




}

