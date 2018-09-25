package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 24.09.2018.
 */

@Entity
@Table(name = "Personmemberships" ,schema = "Rel")
@Data
public class PersonMemberships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons person ;


    @ManyToOne
    @JoinColumn(name = "membership_id")
    private MemberShips memberShips ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status ;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Positions position ;
}
