package az.egov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 26.09.2018.
 */
@Entity
@Data
@Table(name = "Personcontacts" , schema = "Rel")
public class PersonContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    private String contactInfo ;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Persons person ;

}
