package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by admin on 14.09.2018.
 */
@Entity
@Data
@Table(name = "Personfields" , schema = "Rel")
public class PersonFields {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column
    private String fieldRegistryNumber ;

    @Column
    private Float fieldAmount ;


    @ManyToOne
    @JoinColumn(name = "field_amount_unit_id")
    private Units units ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status ;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons person ;


}
