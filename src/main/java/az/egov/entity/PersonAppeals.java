package az.egov.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by admin on 03.09.2018.
 */

@Entity
@Table(name = "Personappeals" , schema = "Rel")
public class PersonAppeals {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id ;

    private String message ;

    @Column
    private Integer statusId ;

    @Column(name = "application_number")
    private String applicationNumber ;
    @Column
    private String longitude ;
    @Column
    private String latitude ;
    @Column
    private String note ;

   /* @Column
    private Integer sort ;

    @Column
    private String label ;*/



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Persons person ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appeal_type_id")
    private AppealTypes appealTypes ;

    public void setMessage(String message) {
        if(message!= null)
         this.message = message;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void setApplicationNumber(String applicationNumber) {
        if(applicationNumber != null)
         this.applicationNumber = applicationNumber;
    }

    public void setLongitude(String longitude) {
        if(longitude != null)
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        if(latitude != null)
        this.latitude = latitude;
    }

    public void setNote(String note) {
        if(note != null)
        this.note = note;
    }

    public void setPerson(Persons person) {
        if(person != null)
        this.person = person;
    }

    public void setAppealTypes(AppealTypes appealTypes) {
        if(appealTypes != null)
         this.appealTypes = appealTypes;
    }

    public BigInteger getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getNote() {
        return note;
    }

    public Persons getPerson() {
        return person;
    }

    public AppealTypes getAppealTypes() {
        return appealTypes;
    }

    /*public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        if(sort != null)
          this.sort = sort;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if(label != null)
          this.label = label;
    }*/



    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n PersonAppeals{" +
                "\n message='" + message + '\'' +
                ", \napplicationNumber='" + applicationNumber + '\'' +
                ", \nlongitude='" + longitude + '\'' +
                ", \nlatitude='" + latitude + '\'' +
                ", \nnote='" + note + '\'' +
               // ", \nsort=" + sort +
                //", \nlabel='" + label + '\'' +
                ", \nperson=" + person +
                ", \nappealTypes=" + appealTypes +
                '}';
    }
}
