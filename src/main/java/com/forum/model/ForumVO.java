package com.forum.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class ForumVO implements Serializable {
    private Integer forum_id;
    private Integer member_id;
    private String content;
    private LocalDateTime date;
    private String title;
    private Integer like;
    private Integer look;
    private Integer message;
    private Integer status;

    public ForumVO() {
    }

    @Override
    public String toString() {
        return "ForumVO{" +
                "forum_id=" + forum_id +
                ", member_id=" + member_id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", like=" + like +
                ", look=" + look +
                ", message=" + message +
                ", status=" + status +
                '}';
    }

    public ForumVO(Integer forum_id, Integer member_id, String content, LocalDateTime date, String title, Integer like, Integer look, Integer message, Integer status) {
        this.forum_id = forum_id;
        this.member_id = member_id;
        this.content = content;
        this.date = date;
        this.title = title;
        this.like = like;
        this.look = look;
        this.message = message;
        this.status = status;
    }

    public Integer getForum_id() {
        return forum_id;
    }

    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
