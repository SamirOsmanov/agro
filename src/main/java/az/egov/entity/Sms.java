package az.egov.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 21.09.2018.
 */

@Entity
@Table(name = "sms" , schema = "List")
@Data
public class Sms {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column
    private String phone  ;

    @Column
    private Integer isVerified ;

    @Column
    private Date createDate = new Date() ;

    public Sms(String phone) {
        this.phone = phone;
    }
}
