package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 25.09.2018.
 */

@Entity
@Data
@Table(name = "Contacttypes" , schema = "List")
public class ContactTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String name ;
}
