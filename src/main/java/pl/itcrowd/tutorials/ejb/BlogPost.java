package pl.itcrowd.tutorials.ejb;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 1/17/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class BlogPost {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String topic;

    public BlogPost()
    {
    }

    public BlogPost(String topic)
    {

        this.topic = topic;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "BlogPost{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                '}';
    }
}