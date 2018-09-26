package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 26.09.2018.
 */

@Entity
@Data
@Table(name = "Addresstypes" , schema = "List")
public class AddressTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String name ;
}
