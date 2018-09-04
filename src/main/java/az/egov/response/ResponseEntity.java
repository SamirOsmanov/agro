package az.egov.response;

import java.util.Date;

/**
 * Created by admin on 04.09.2018.
 */
public class ResponseEntity<T> {

    private int  status ;
    private T    data   ;
    private Date time ;

    public ResponseEntity(int status, T data, Date time) {
        this.status = status;
        this.data = data;
        this.time = time;
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
}
