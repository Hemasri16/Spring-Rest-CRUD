package com.dxc.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private Date dob;
	private String email;
	private String mobile;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String dobth, String email, String mobile) throws ParseException {
		super();
		this.id = id;
		this.name = name;
		
			SimpleDateFormat date= new SimpleDateFormat("dd-MM-yyyy");
			dob =date.parse(dobth);
		
		this.email = email;
		this.mobile = mobile;
	}

    
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    @Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="dob",nullable=false)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="mobile",nullable=false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		String dobth = new SimpleDateFormat("dd-MM-YYYY").format(dob);
		return "Student [id=" + id + ", name=" + name + ", dob=" + dobth + ", email=" + email + ", mobile=" + mobile + "]";
	}
}
