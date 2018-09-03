package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by admin on 03.09.2018.
 */
@Data
@Entity
@Table(name = "PersonAppeals" , schema = "Rel")
public class PersonAppeals {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id ;
}
