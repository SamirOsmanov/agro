package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 27.09.2018.
 */

@Entity
@Data
@Table(name = "Personactivityareas" ,  schema = "Rel")
public class PersonActivityAreas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons personId ;

    @ManyToOne
    @JoinColumn(name = "active_area_id")
    private ActivityAreas activityId ;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Areas areaId ;
}
