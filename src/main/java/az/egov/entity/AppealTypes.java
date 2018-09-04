package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 04.09.2018.
 */
@Data
@Entity
@Table(name = "Appealtypes" , catalog = "List")
public class AppealTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String name ;

    public AppealTypes(Integer id)
    {
        this.id = id ;
    }

}
