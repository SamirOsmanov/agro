package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 17.09.2018.
 */

@Table(name = "Userroles", schema = "Rel")
@Entity
@Data
public class UserRoles {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @ManyToOne
    private Roles role ;

    @ManyToOne
    private Users user ;

}
