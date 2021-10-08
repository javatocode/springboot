package com.sboot.book.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorid;
	@Column(name = "First_Name")
	private String fname;
	@Column(name = "Last_Name")
	private String lname;
	@Column(name = "Language")
	private String Lang;

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLang() {
		return Lang;
	}

	public void setLang(String lang) {
		Lang = lang;
	}

	public Author(int authorid, String fname, String lname, String lang) {
		super();
		this.authorid = authorid;
		this.fname = fname;
		this.lname = lname;
		Lang = lang;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Author [authorid=" + authorid + ", fname=" + fname + ", lname=" + lname + ", Lang=" + Lang + "]";
	}

}
