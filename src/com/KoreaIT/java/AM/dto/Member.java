package com.KoreaIT.java.AM.dto;

public class Member extends Dto{
	public String mid;
	public String mpwd;
	public String mname;
	
	public Member(int idx, String datetime, String id, String pwd, String name) {
		this.id = idx;
		this.datetime = datetime;
		this.mid = id;
		this.mpwd = pwd;
		this.mname = name;
	}
}
