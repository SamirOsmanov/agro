package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 24.09.2018.
 */

@Entity
@Data
@Table(name = "Activityareas" , schema = "List")
public class ActivityAreas {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @Column
    private String name ;

    public ActivityAreas(Integer id)
    {
        this.id = id ;
    }

}
