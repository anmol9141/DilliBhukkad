package com.example.anmol.dillikebhukkad;

/**
 * Created by Anmol on 7/27/2017.
 */

public class Post {

    String id;
    String imgURL,content,title,username,dpURL,time;
    boolean like,bMark;

    public Post(){

    }

    public Post(String imgURL, String content, String title, String username, String dpURL, String time, boolean like, boolean bMark, String id) {
        this.imgURL = imgURL;
        this.content = content;
        this.title = title;
        this.username = username;
        this.dpURL = dpURL;
        this.time = time;
        this.like = like;
        this.bMark = bMark;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDpURL() {
        return dpURL;
    }

    public void setDpURL(String dpURL) {
        this.dpURL = dpURL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isbMark() {
        return bMark;
    }

    public void setbMark(boolean bMark) {
        this.bMark = bMark;
    }
}
