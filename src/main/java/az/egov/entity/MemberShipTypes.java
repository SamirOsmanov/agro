package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 24.09.2018.
 */


@Entity
@Table(name = "Membershiptypes" , schema = "List")
@Data
public class MemberShipTypes {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @Column
    private String name ;

    public MemberShipTypes() {
    }

    public MemberShipTypes(Integer id) {
        this.id = id ;
    }


}
