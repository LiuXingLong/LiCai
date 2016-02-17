package com.example.object;
// ”√ªß¿‡
public class User {
	private int uid;
	private String name;
	private String pass;
	public User(){
		super();
	}
	public User(int uid,String name,String pass){
		super();
		this.uid=uid;
		this.name=name;
		this.pass=pass;		
	}
	public int getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}	
}
