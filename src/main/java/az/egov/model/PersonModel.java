package az.egov.model;

import lombok.Data;

/**
 * Created by admin on 12.09.2018.
 */
@Data
public class PersonModel {

    private String pin ;
    private String firstName ;
    private String lastName ;
    private String middleName ;

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

