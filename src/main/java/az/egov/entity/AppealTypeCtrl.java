package az.egov.entity;

import javax.persistence.*;

/**
 * Created by admin on 17.09.2018.
 */
@Table( name = "Appealtypecontroller" ,schema = "Rel")
@Entity
public class AppealTypeCtrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private Users user ;

    @JoinColumn(name = "appeal_type_id")
    @ManyToOne
    private AppealTypes appealType ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public AppealTypes getAppealType() {
        return appealType;
    }

    public void setAppealType(AppealTypes appealType) {
        this.appealType = appealType;
    }
}
