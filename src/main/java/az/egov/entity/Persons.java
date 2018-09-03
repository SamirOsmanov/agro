package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by admin on 03.09.2018.
 */

@Data
@Entity
@Table(name = "Persons" , schema = "List")
public class Persons {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id ;

    @Column(name = "ID_SOGGETTO")
    private Integer idSoggetto ;

    @Column(length = 7)
    private String pin ;

    @Column(name = "first_name" , length = 100)
    private String firstName ;

    @Column(name = "last_name", length = 100)
    private String lastName ;

    @Column(name = "father_name", length = 100)
    private String fatherName ;

    @Column(length = 50)
    private String ssn ;

    @Column(length = 1000 , name = "organization_name")
    private String organizationName ;

    @Column(length = 30)
    private String voen ;

    @Temporal(TemporalType.DATE)
    @Column(name = "voen_created_date")
    private Date voenCreatedDate ;

    @Temporal(TemporalType.DATE)
    @Column(name = "voen_expired_date")
    private Date voenExpiredDate ;

    @Column
    private String note ;

    @Column(name = "is_registered")
    private boolean isRegistered ;

    @Column(name = "is_archived")
    private boolean isArchived ;

    private Integer sort ;

    @Column(length = 50)
    private String label ;

    /*@Column(name = "create_user_id")
    private Integer createUserId ;*/

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate ;

    /*@Column(name = "status_id")
    private Integer statusId ;*/

    // add transaction id





}
