package az.egov.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by admin on 12.09.2018.
 */
@Data
public class PersonModel {

    private String  pin ;
    private String  firstName ;
    private String  lastName ;
    private String  middleName ;
    private String  gender ;
    private String  policeDept ;
    private Integer sosialStatus ;
    private String  issueDate ;
    private String  birth ;
    private String  expDate ;

    @Override
    public String toString() {
        return "PersonModel{" +
                "pin='" + pin + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}

