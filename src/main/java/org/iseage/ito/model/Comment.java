package org.iseage.ito.model;

public class Comment {

    private long id;
    private String comment;
    private String author;

    public Comment(long id, String comment, String author) {
        this.id = id;
        this.comment = comment;
        this.author = author;
    }

    public Comment(String comment, String author) {
        this.id = -1;
        this.comment = comment;
        this.author = author;
    }

    public Comment() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
