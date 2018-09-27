package az.egov.entity;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by admin on 27.09.2018.
 */

@Entity
@Table(name = "Areas" , schema = "List")
public class Areas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;


    @Column
    private String name ;


    @Column
    private Integer areaTypeId ;


    @Column
    private Integer parentId ;

    @Column
    private Integer statusId ;

    public Areas() {
    }

    public Areas(Integer id) {
        this.id = id ;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAreaTypeId(Integer areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
