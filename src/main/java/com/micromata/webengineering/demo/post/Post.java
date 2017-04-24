package com.micromata.webengineering.demo.post;

/**
 * A single Post in the Forum
 */
public class Post {

    // the identifier for this Post
    private int id;

    // the title of the Post
    private String title;

    // the content of the Post
    private String content;

    // the timestamp of the creation
    private long timestamp;

    public Post (String title, int id) {
        this(title, "", id);
    }

    public Post (String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
