package az.egov.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by admin on 10.09.2018.
 */

@Entity
@Table(name = "notifications" , schema = "Rel")
public class Notifications {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id ;


    @Column
    private String message ;

    @Column(name = "firebase_id")
    private String firebaseId ;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons person ;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessageId(String message) {
        this.message = message;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPersons(Persons person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "\n Notifications{" +
                "\n id=" + id +
                ",\n message='" + message + '\'' +
                ",\n firebaseId='" + firebaseId + '\'' +
                ",\n person=" + person +
                "\n}";
    }
}
