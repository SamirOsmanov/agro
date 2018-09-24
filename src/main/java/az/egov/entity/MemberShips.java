package az.egov.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 24.09.2018.
 */
@Entity
@Table(name = "Memberships" ,schema = "List")
public class MemberShips {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column
    private String address ;

    @Column
    private String name ;

    @Column
    private String phoneNumber ;

    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MemberShipTypes memberShipTypes;

    @ManyToOne
    @JoinColumn(name = "activity_area_id")
    private ActivityAreas activityAreas ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address != null)
          this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null)
          this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber!= null)
         this.phoneNumber = phoneNumber;
    }

    public MemberShipTypes getMemberShipTypes() {
        return memberShipTypes;
    }

    public void setMemberShipTypes(MemberShipTypes memberShipTypes) {
        if(memberShipTypes != null)
         this.memberShipTypes = memberShipTypes;
    }

    public ActivityAreas getActivityAreas() {
        return activityAreas;
    }

    public void setActivityAreas(ActivityAreas activityAreas) {
        if(activityAreas != null)
         this.activityAreas = activityAreas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if(status != null)
         this.status = status;
    }
}
