package az.egov.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by admin on 03.09.2018.
 */

@Entity
@Table(name = "Persons" , schema = "List")
public class Persons {


    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column( columnDefinition="uniqueidentifier")
    private String id ;


    @Column(length = 7)
    private String pin ;

    @Column(name = "first_name" , length = 100)
    private String firstName ;

    @Column(name = "last_name", length = 100)
    private String lastName ;

    @Column(name = "father_name", length = 100)
    private String fatherName ;

    @Column(length = 50)
    private String ssn ;

    @Column(length = 1000 , name = "organization_name")
    private String organizationName ;

    @Column(length = 30)
    private String voen ;

    @Column(length = 20)
    private String phone ;

    @Column
    private Integer statusId ;

    //@Temporal(TemporalType.DATE)
    @Column(name = "voen_created_date")
    private Date voenCreatedDate ;

    //@Temporal(TemporalType.DATE)
    @Column(name = "voen_expired_date")
    private Date voenExpiredDate ;

    @Column
    private String note ;

    @Column
    private String label ;

    @Column
    private  Date createDate = new Date();


    public Persons() {
    }

    public Persons(String id)
    {
        this.id = id ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone!= null)
        this.phone = phone;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName!= null)
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName!= null)
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        if(fatherName!= null)
        this.fatherName = fatherName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if(ssn != null)
        this.ssn = ssn;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        if(organizationName!= null)
        this.organizationName = organizationName;
    }

    public String getVoen() {
        return voen;
    }

    public void setVoen(String voen) {
        if (voen!= null)
        this.voen = voen;
    }

    public Date getVoenCreatedDate() {
        return voenCreatedDate;
    }

    public void setVoenCreatedDate(Date voenCreatedDate) {
        if(voenCreatedDate != null)
        this.voenCreatedDate = voenCreatedDate;
    }

    public Date getVoenExpiredDate() {
        return voenExpiredDate;
    }

    public void setVoenExpiredDate(Date voenExpiredDate) {
        if(voenExpiredDate!= null)
        this.voenExpiredDate = voenExpiredDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        if(note != null)
        this.note = note;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if(label!= null)
        this.label = label;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        if(createDate != null)
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "\n Person{" +
                " \npin='" + pin + '\'' +
                ",\n firstName='" + firstName + '\'' +
                ",\n lastName='" + lastName + '\'' +
                ",\n fatherName='" + fatherName + '\'' +
                ",\n ssn='" + ssn + '\'' +
                ",\n organizationName='" + organizationName + '\'' +
                ",\n voen='" + voen + '\'' +
                ",\n phone='" + phone + '\'' +
                ",\n voenCreatedDate=" + voenCreatedDate +
                ",\n voenExpiredDate=" + voenExpiredDate +
                ",\n note='" + note + '\'' +
                ",\n label='" + label + '\'' +
                "} \n";
    }
}
