package com.micromata.webengineering.myforum.post;

import com.micromata.webengineering.myforum.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * A single Post in the Forum
 */
@Entity
public class Post {

    public static final int TITLE_LENGTH = 1024;

    // the identifier for this Post
    @Id
    @GeneratedValue
    private long id;

    // the title of the Post
    @Column(length = TITLE_LENGTH)
    private String title;

    // the date of the creation
    private Date timestamp;

    // the author of this post
    @ManyToOne(optional = false)
    private User author;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    @PrePersist
    public void createTimestamp() {
        this.setTimestamp(new Date());
    }

    @Override
    public String toString() {
        return "Post{"+
                "id="+ id +
                ", author=" + author +
                ", title=" + title +
                ", timestamp=" + timestamp +
                '}';
    }
}
