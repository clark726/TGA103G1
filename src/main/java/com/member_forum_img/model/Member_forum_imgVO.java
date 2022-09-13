package com.member_forum_img.model;

import java.io.Serializable;

public class Member_forum_imgVO implements Serializable {
    private Integer member_forum_img_id;
    private Integer forum_id;
    private byte[] img;

    public Member_forum_imgVO(Integer member_forum_img_id, Integer forum_id, byte[] img) {
        this.member_forum_img_id = member_forum_img_id;
        this.forum_id = forum_id;
        this.img = img;
    }

    public Member_forum_imgVO() {
    }

    public Integer getMember_forum_img_id() {
        return member_forum_img_id;
    }

    public void setMember_forum_img_id(Integer member_forum_img_id) {
        this.member_forum_img_id = member_forum_img_id;
    }

    public Integer getForum_id() {
        return forum_id;
    }

    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
