package az.egov.controller;

import az.egov.entity.Notifications;
import az.egov.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by admin on 10.09.2018.
 */

@RestController
@RequestMapping("/api/notification")
public class NotificationController {


    @Autowired
    NotificationsService notificationsService ;

    @PostMapping("/save")
    public Object saveNotification(@RequestBody Notifications notifications)
    {
       return notificationsService.save(notifications) ;
    }

    @GetMapping("/{id}")
    public Object findNotification(@PathVariable("id") BigInteger id)
    {
        return notificationsService.findById(id) ;
    }

    @GetMapping("/list")
    public Object notificationPagination(@RequestParam("offset") Integer offset,
                                         @RequestParam("fetch")  Integer fetch)
    {
        HashMap<String,Object> response = new HashMap<>() ;
        response.put("items", notificationsService.list(offset,fetch));
        response.put("totalCount",notificationsService.totalCount()) ;
        return response ;
    }


}
