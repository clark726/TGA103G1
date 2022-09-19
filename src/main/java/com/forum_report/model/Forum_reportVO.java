package com.forum_report.model;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Forum_reportVO  implements Serializable {
    private Integer forum_report_id;
    private Integer member_id;
    private String reason;
    private LocalDateTime date;
    private Integer forum_id;
    private Integer status;

    public Integer getForum_report_id() {
        return forum_report_id;
    }

    public void setForum_report_id(Integer forum_report_id) {
        this.forum_report_id = forum_report_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getForum_id() {
        return forum_id;
    }

    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Forum_reportVO [forum_report_id=" + forum_report_id + ", member_id=" + member_id + ", reason=" + reason
				+ ", date=" + date + ", forum_id=" + forum_id + ", status=" + status + "]";
	}
    
    
}