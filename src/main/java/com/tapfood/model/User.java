package com.tapfood.model;

public class User {
	private int userid;
	private String username;
	private String email;
	private String phonenumber;
	private String password;
	private String address;
	
	public User() {
		super();
	}

	public User(int userid, String username, String email, String phonenumber, String password, String address) {
		
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.address = address;
	}

	public User(String username, String email, String phonenumber, String password, String address) {
		
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.address = address;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", phonenumber=" + phonenumber
				+ ", password=" + password + ", address=" + address + "]";
	}
	
}
