package com.micromata.webengineering.myforum.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micromata.webengineering.myforum.comment.Comment;
import com.micromata.webengineering.myforum.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A single Post in the Forum
 */
@Entity
public class Post {

    public static final int TITLE_LENGTH = 1024;

    // the identifier for this Post
    @Id
    @JsonIgnore
    @GeneratedValue
    private long id;

    // the title of the Post
    @Column(length = TITLE_LENGTH)
    private String title;

    // the date of the creation
    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // the author of this post
    @ManyToOne(optional = false)
    private User author;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Post (String title) {
        this.title = title;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
