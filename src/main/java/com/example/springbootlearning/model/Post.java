package com.example.springbootlearning.model;

import io.swagger.annotations.ApiModelProperty;

public class Post {
    @ApiModelProperty(notes = "User Id")
    private int userId ;
    @ApiModelProperty(notes = "post id")
    private int id;
    @ApiModelProperty(notes = "post title")
    private String title;
    @ApiModelProperty(notes = "Post message")
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
