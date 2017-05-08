package com.micromata.webengineering.demo.post;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * A single Post in the Forum
 */
@Entity
public class Post {

    // the identifier for this Post
    @Id
    @GeneratedValue
    private long id;

    // the title of the Post
    private String title;

    // the date of the creation
    private Date timestamp;

    public Post() {
        this("");
    }

    public Post (String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrePersist
    public void createTimestamp() {
        this.setTimestamp(new Date());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
