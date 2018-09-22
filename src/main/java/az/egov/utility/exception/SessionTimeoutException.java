package az.egov.utility.exception;

/**
 * Created by admin on 18.09.2018.
 */
public class SessionTimeoutException extends RuntimeException {

    public SessionTimeoutException(String message) {
        super(message);
    }
}
