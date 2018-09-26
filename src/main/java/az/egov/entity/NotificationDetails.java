package az.egov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 26.09.2018.
 */
@Entity
@Data
@Table(name = "Notificationdetails", schema = "Rel")
public class NotificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @ManyToOne
    @JoinColumn(name = "notification_sent_type_id")
    private NotificationSentTypes sentTypes ;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification ;

    @Column
    private Date sentDate ;

    @Column
    private Integer statusId = 1 ;
}
