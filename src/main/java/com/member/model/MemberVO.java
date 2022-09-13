package com.member.model;

import java.time.*;

public class MemberVO {

    private Integer member_id;
    private String account;
    private String password;
    private LocalDate birthday;
    private String address;
    private Integer gender;
    private String email;
    private String nickname;
    private String phone;
    private LocalDateTime register;
    private Integer permission;
    public MemberVO() {}
    
    public MemberVO(String account, String password, LocalDate birthday, String address, Integer gender, String email,
			String nickname, String phone, Integer permission) {
		super();
		this.account = account;
		this.password = password;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.email = email;
		this.nickname = nickname;
		this.phone = phone;
		this.permission = permission;
	}

	public MemberVO(Integer member_id, String account, String password, LocalDate birthday, String address, Integer gender,
                    String email, String nickname, String phone, LocalDateTime register, Integer permission) {
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
    public Integer getMember_id() {
        return member_id;
    }
    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDateTime getRegister() {
        return register;
    }
    public void setRegister(LocalDateTime register) {
        this.register = register;
    }
    public Integer getPermission() {
        return permission;
    }
    public void setPermission(Integer permission) {
        this.permission = permission;
    }
    @Override
    public String toString() {
        return "MemberVO [member_id=" + member_id + ", account=" + account + ", password=" + password + ", birthday="
                + birthday + ", address=" + address + ", gender=" + gender + ", email=" + email + ", nickname="
                + nickname + ", phone=" + phone + ", register=" + register + ", permission=" + permission + "]";
    }
}
