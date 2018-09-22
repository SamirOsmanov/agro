package az.egov.utility.helper;

/**
 * Created by admin on 17.09.2018.
 */
public enum Roles {

    MODERATOR("moderator") ,
    ADMIN("administrator") ,
    CONTROLLER("controller") ;

    private String type ;

    Roles(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
