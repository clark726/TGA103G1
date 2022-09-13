package com.message_report.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Message_reportVO implements Serializable{
	   private Integer message_report_id;
	    private Integer member_id;
	    private Integer message_id;
	    private String reason;
	    private LocalDate date;
	    private Integer status;

	    
	    public Message_reportVO() {
	    }
	    
	    public Message_reportVO(Integer message_report_id, Integer member_id, Integer message_id ,String reason, LocalDate date,
				Integer status) {
			super();
			this.message_report_id = message_report_id;
			this.member_id = member_id;
			this.message_id = message_id;
			this.reason = reason;
			this.date = date;
			this.status = status;
		}

		public Integer getMessage_report_id() {
	        return message_report_id;
	    }

	    public void setMessage_report_id(Integer message_report_id) {
	        this.message_report_id = message_report_id;
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

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public Integer getMessage_id() {
	        return message_id;
	    }

	    public void setMessage_id(Integer message_id) {
	        this.message_id = message_id;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }
	}

