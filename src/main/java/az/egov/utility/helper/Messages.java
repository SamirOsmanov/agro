package az.egov.utility.helper;

/**
 * Created by admin on 03.09.2018.
 */
public enum Messages {

    TOKEN_NOT_FOUND("Token's value not found in the request parameter") ;

    private String messageContent ;

    Messages(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
