package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 24.09.2018.
 */
@Entity
@Table(schema = "List")
@Data
public class Status {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column
    private String name ;

    public Status(Integer id)
    {
        this.id = id ;
    }
}
