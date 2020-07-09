package com.wu.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CustomerDto implements Serializable {
	
	private static final long serialVersionUID = -7966329949804750134L;
	
	private Long   phoneNo;
	private String name;
	private String email;
	private int    age;
	private String gender;
	private String password;
	private String address;
	
	public CustomerDto() {
		
	}

	public CustomerDto(Long phoneNo, String name, String email, int age, String gender, String password,
			String address) {
		
		this.phoneNo = phoneNo;
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.password = password;
		this.address = address;
	
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CustomerDto [phoneNo=" + phoneNo + ", name=" + name + ", email=" + email + ", age=" + age + ", gender="
				+ gender + ", password=" + password + ", address=" + address + "]";
	}
	
}
