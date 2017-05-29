package com.micromata.webengineering.myforum.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micromata.webengineering.myforum.post.Post;
import com.micromata.webengineering.myforum.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    public static final int TEXT_LENGTH = 1024;

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    // the author of this comment
    @ManyToOne(optional = false)
    private User author;

    @Column(length = TEXT_LENGTH)
    private String text;

    // the date of the creation
    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
