package az.egov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 26.09.2018.
 */
@Entity
@Table(name = "Notificationsactivityareas" , schema = "Rel")
@Data
public class NotificationsActivityAreas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    @JsonIgnore
    private Notification notification ;

    @ManyToOne
    @JoinColumn(name = "activity_area_id")
    private ActivityAreas activityAreaId ;
}
