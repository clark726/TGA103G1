package com.forum_message.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Forum_messageVO implements Serializable {
	private Integer message_id;
	private Integer member_id;
	private Integer forum_id;
	private String content;
	private LocalDateTime date;

	public Forum_messageVO() {}
	public Forum_messageVO(Integer member_id,Integer forum_id,String content) {
		super();
		this.member_id = member_id;
		this.forum_id = forum_id;
		this.content = content;

	}
	public Forum_messageVO(Integer member_id,Integer forum_id,String content,LocalDateTime date) {
		super();
		this.member_id = member_id;
		this.forum_id = forum_id;
		this.content = content;
		this.date = date;
	}
	
	public Forum_messageVO(Integer message_id, Integer member_id,Integer forum_id,String content,LocalDateTime date) {
        super();
        this.message_id = message_id;
        this.member_id = member_id;
		this.forum_id = forum_id;
		this.content = content;
		this.date = date;
    }
	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getForum_id() {
		return forum_id;
	}

	public void setForum_id(Integer forum_id) {
		this.forum_id = forum_id;
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
	
	@Override
    public String toString() {
        return "Forum_messageVO [message_id=" + message_id + ", member_id=" + member_id + ",forum_id=" + forum_id +",content=" + content +",date=" + date+ "]";
    }
}