package com.example.object;
public class Note {
	private int id;
	private int uid;
	private String info;
	public Note(){
		super();
	}
	public Note(int id,int uid,String info){
		super();
		this.id=id;
		this.uid=uid;
		this.info=info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
