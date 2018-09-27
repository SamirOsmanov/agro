package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by admin on 26.09.2018.
 */

@Data
@Table(name = "notifications" , schema = "Rel")
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private BigInteger id ;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Areas area ;

    @Column
    private String title ;

    @Column
    private String message ;

    @ManyToOne
    @JoinColumn(name = "notification_priority_id")
    private NotificationPriorities priority ;

    @OneToMany(mappedBy = "notification")
    private  List<NotificationsActivityAreas> notificationsActivityAreas ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status ;

    public Notification(BigInteger id) {
        this.id = id ;
    }

    public Notification() {
    }
}
