package az.egov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "activityAreas")
    @JsonIgnore
    private List<MemberShips> memberShips = new ArrayList<>();

}
