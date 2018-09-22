package az.egov.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by admin on 10.09.2018.
 */
@Entity
@DynamicUpdate
@Table(name = "news" , schema = "List" )
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String title ;

    @Column
    private String subTitle ;

    @Column
    private Integer statusId ;

    @Column
    private String description ;

    @Column
    private String content ;

    //@Temporal(TemporalType.DATE)
    @Column
    private Date publish ;



    public News() {
    }

    @Column
    private byte[] image ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title!=null)
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description!=null)
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content!=null)
        this.content = content;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        if(publish!=null)
        this.publish = publish;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        if(image!= null)
        this.image = image;
    }


    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        if (statusId!= null)
        this.statusId = statusId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        if(subTitle != null)
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", publish=" + publish +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
