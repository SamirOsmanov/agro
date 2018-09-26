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

    @Column
    private Integer areaId ;

    @ManyToOne
    @JoinColumn(name = "notification_priority_id")
    private NotificationPriorities priority ;

    @Column
    private Integer statusId = 1 ;

    public Notification(BigInteger id) {
        this.id = id ;
    }

    public Notification() {
    }
}
