package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 14.09.2018.
 */

@Entity
@Data
@Table(name = "units" , schema = "List")
public class Units {

    @Id
    @Column
    private Integer id ;

    @Column
    private String name ;

    public Units(Integer id) {
        this.id = id;
    }

    public Units() {
    }
}
