package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 17.09.2018.
 */
@Entity
@Table(name = "roles" , schema = "List")
@Data
public class Roles
{
     @Id
     @Column
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id ;

     @Column
     private String name ;

    public Roles() {
    }

    public Roles(Integer id) {
        this.id = id;
    }
}
