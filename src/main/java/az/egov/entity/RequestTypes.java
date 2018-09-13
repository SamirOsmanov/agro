package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 11.09.2018.
 */
@Entity
@Table(name = "Requesttypes" , schema = "List")
public class RequestTypes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String name ;

    public RequestTypes(Integer id) {
        this.id = id ;
    }

    public Integer getId() {
        return id;
    }

    public RequestTypes() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
