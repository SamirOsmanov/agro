package az.egov.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by admin on 10.09.2018.
 */
@Entity
@Table(name = "news" , schema = "List")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id ;

    @Column
    private String title ;

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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", publish=" + publish +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
