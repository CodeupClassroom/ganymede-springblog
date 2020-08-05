package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    public Post(){}

    public Post(long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setBody(String newBody){
        this.body = newBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
