package com.member.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.Result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member",catalog = "barjarjo")
public class MemberVO extends Result implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer member_id;
	private String account;
	private String password;
	private LocalDate birthday;
	private String address;
	private Integer gender;
	private String email;
	private String nickname;
	private String phone;
	@Column(insertable = false)
	private LocalDate register;
	@Column(insertable = false)
	private Integer permission;
	@Transient
	private String verifyAccount;
	@Transient
	private String newPassword;
	@Transient
	private String inputCode;

	public MemberVO(Integer member_id, String account, String password, LocalDate birthday, String address, Integer gender,
			String email, String nickname, String phone, LocalDate register, Integer permission) {
		super();
		this.member_id = member_id;
		this.account = account;
		this.password = password;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.email = email;
		this.nickname = nickname;
		this.phone = phone;
		this.register = register;
		this.permission = permission;
	}

}
