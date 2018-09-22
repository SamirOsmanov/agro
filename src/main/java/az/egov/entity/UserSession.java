package az.egov.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 13.09.2018.
 */

@Entity
@Table(name = "Usersession" , schema = "List")
public class UserSession {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column(name = "value")
    private String sessionId ;

    @Column(name = "status_id")
    private Integer statusId ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user ;

    @Column
    private String userAgent ;
    @Column
    private Date   lastActivity ;
    @Column
    private String ip ;

    public UserSession(String sessionId, Integer statusId) {
        this.sessionId = sessionId;
        this.statusId = statusId;
    }

    public UserSession() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", statusId=" + statusId +
                ", user=" + user +
                ", userAgent='" + userAgent + '\'' +
                ", lastActivity=" + lastActivity +
                ", ip='" + ip + '\'' +
                '}';
    }
}
