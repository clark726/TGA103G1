package com.like.model;

import java.io.Serializable;

public class LikeVO implements Serializable {
    private Integer forum_id;
    private Integer member_id;

    public LikeVO(Integer forum_id, Integer member_id) {
        this.forum_id = forum_id;
        this.member_id = member_id;
    }

    public LikeVO() {
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
}
