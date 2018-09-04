package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by admin on 03.09.2018.
 */
@Data
@Entity
@Table(name = "Personappeals" , schema = "Rel")
public class PersonAppeals {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id ;

    private String message ;

    @Column(name = "application_number")
    private String applicationNumber ;
    @Column
    private String longitude ;
    @Column
    private String latitude ;
    @Column
    private String note ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Persons person ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appeal_type_id")
    private AppealTypes appealTypes ;



}
