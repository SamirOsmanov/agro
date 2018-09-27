package az.egov.implementation;

import az.egov.entity.*;
import az.egov.repository.NotificationDetailsRepository;
import az.egov.repository.NotificationRepository;
import az.egov.repository.NotificationSentTypesRepository;
import az.egov.repository.NotificationsActivityAreasRepository;
import az.egov.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 26.09.2018.
 */

@Service
public class NotificationImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository ;

    @Autowired
    private NotificationDetailsRepository detailsRepository ;


    @Autowired
    private NotificationsActivityAreasRepository activityAreasRepository ;

    @PersistenceContext
    EntityManager em ;


    @Override
    public NotificationDetails findByNotificationId(Object id) {
        BigInteger notificationId = (BigInteger) id;
        Notification notification = new Notification(notificationId)  ;
        return detailsRepository.findByNotification(notification) ;
    }

    @Override
    public List<Notification> list(Integer offset, Integer fetch) {
        return  em.createQuery("from NotificationDetails as n where n.statusId != 3")
                .setFirstResult(offset)
                .setMaxResults(fetch)
                .getResultList() ;
    }

    @Override
    public Long totalCount() {
        return notificationRepository.totalCount();
    }

    @Override
    public Notification saveNotification(List< HashMap<String, Object> > list) throws ParseException {


        for(HashMap<String, Object> request : list) {

            Integer priority = (Integer) request.get("priority");
            Integer areaId = (Integer) request.get("areaId");
            String title = (String) request.get("title");
            String message = (String) request.get("message");

            List<String> sendDates = (List<String>) request.get("sendDates");
            List<Integer> sendTypes = (List<Integer>) request.get("sendTypes");
            List<Integer> activityAreas = (List<Integer>) request.get("activityId");

            NotificationPriorities priorities = new NotificationPriorities(priority);

            Notification notification = new Notification();
            notification.setPriority(priorities);
            notification.setTitle(title);
            notification.setMessage(message);

            Areas area = new Areas(areaId) ;
            //notification.setStatus(new Status(1));
            notification.setArea(area);

            Notification savedNotification = notificationRepository.save(notification);

            // *****************************************

            if (sendTypes != null) {

                int index = 0;
                for (Integer sendType : sendTypes) {


              /*  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(sendDates.get(index));*/

                    NotificationDetails notificationDetails = new NotificationDetails();
                    notificationDetails.setNotification(savedNotification);
                    notificationDetails.setSentDate(new Date());
                    NotificationSentTypes notificationSentType = new NotificationSentTypes(sendType);
                    notificationDetails.setSentTypes(notificationSentType);

                    detailsRepository.save(notificationDetails);
                    index++;
                }
            }

            // *****************************************


            if (activityAreas != null) {

                for (Integer activityId : activityAreas) {
                    NotificationsActivityAreas notificationActivity = new NotificationsActivityAreas();
                    notificationActivity.setNotification(notification);

                    ActivityAreas activityArea = new ActivityAreas(activityId) ;

                    notificationActivity.setActivityAreaId(activityArea);

                    activityAreasRepository.save(notificationActivity);
                }
            }
        }



        return null ;


    }
}
