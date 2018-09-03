package az.egov.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by admin on 03.09.2018.
 */
@Data
public class PersonAppealsModel {

    private BigInteger id ;
    private Date    createDate ;
    private String  personId ;
    private String firstName ;
    private String lastName ;
    private String fatherName ;
    private String message ;
    private Integer requestId ;
    private String requestName ;
    private Integer appealId ;
    private String appealName ;

    public PersonAppealsModel(BigInteger id,
                              Date createDate,
                              String personId,
                              String firstName,
                              String lastName,
                              String fatherName,
                              String message,
                              Integer requestId,
                              String requestName,
                              Integer appealId,
                              String appealName)
    {
        this.id = id;
        this.createDate = createDate;
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.message = message;
        this.requestId = requestId;
        this.requestName = requestName;
        this.appealId = appealId;
        this.appealName = appealName;
    }

    public PersonAppealsModel(BigInteger id, Date createDate) {
        this.id = id;
        this.createDate = createDate;
    }
}
