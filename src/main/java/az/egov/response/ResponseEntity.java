package az.egov.response;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by admin on 04.09.2018.
 */
@Document(collection = "log")
public class ResponseEntity<T> {

    @Id
    private ObjectId id ;

    private long totalCount ;

    @Field
    private int    status ;
    @Field
    private T      data   ;
    @Field
    private Date time ;
    @Field
    private String desc ;

    public ResponseEntity(int status, T data,String desc , Date time) {
        this.status = status;
        this.data = data;
        this.time = time;
        this.desc = desc ;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Date getTime() {
        return time;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public String getDesc() {
        return desc;
    }
}
