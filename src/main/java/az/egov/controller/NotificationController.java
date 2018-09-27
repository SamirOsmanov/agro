package az.egov.controller;

import az.egov.entity.Notifications;
import az.egov.service.NotificationService;
import az.egov.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 10.09.2018.
 */

@RestController
@RequestMapping("/api/notification")
public class NotificationController {


   /* @Autowired
    NotificationsService notificationsService ;*/

    @Autowired
    NotificationService notificationService ;


    /*@PostMapping("/save")
    public Object saveNotification(@RequestBody Notifications notifications)
    {
       return notificationsService.save(notifications) ;
    }
*/
    @PostMapping("/save")
    public Object saveNotification(@RequestBody List< HashMap<String,Object> > request) throws ParseException {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            notificationService.saveNotification(request) ;
            response.put("success",true) ;
        }
        catch (Exception e)
        {
            response.put("success",false) ;
        }
        finally {
            return response ;
        }

    }

    @GetMapping("/{id}")
    public Object findNotification(@PathVariable("id") BigInteger id)
    {
        return notificationService.findByNotificationId(id) ;
    }

    @GetMapping("/list")
    public Object notificationPagination(@RequestParam("offset") Integer offset,
                                         @RequestParam("fetch")  Integer fetch)
    {
        HashMap<String,Object> response = new HashMap<>() ;
        response.put("items", notificationService.list(offset,fetch));
        response.put("totalCount",notificationService.totalCount()) ;
        return response ;
    }


}
