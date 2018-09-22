package az.egov.utility.helper;

/**
 * Created by admin on 17.09.2018.
 */
public enum OperationStatus {

    ACTIVE_USER_STATUS(1) ,
    UPDATE_USER_STATUS(2) ,
    SESSION_EXPIRED(2) ,

    UPDATE_STATUS(2) ,
    INSERT_STATUS(1) ,
    DELETE_STATUS(3) ,
    IS_NOT_VERIFIED(0) ,
    IS_VERIFIED(1) ;

    private Integer statusId ;

    OperationStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
