package az.egov.controller;

import az.egov.entity.Notifications;
import az.egov.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * Created by admin on 10.09.2018.
 */

@RestController
@RequestMapping("/agroculture/notification")
public class NotificationController {


    @Autowired
    NotificationsService notificationsService ;

    @PostMapping("/save")
    public Object saveNotification(@RequestBody Notifications notifications)
    {
       return notificationsService.save(notifications) ;
    }

    @GetMapping("/find")
    public Object findNotification(@RequestParam("id") BigInteger id)
    {
        return notificationsService.findById(id) ;
    }

    @GetMapping("/list")
    public Object notificationPagination(@RequestParam("offset") Integer offset,
                                         @RequestParam("fetch")  Integer fetch)
    {
        return notificationsService.list(offset,fetch) ;
    }
}
