package com.example.object;

public class Outlay {
	private int id;
	private int uid;
	private String money;
	private String time;
	private String category;
	private String place;
	private String remark;
	public Outlay(){
		super();
	}
	public Outlay(int id,int uid,String money,String time,String category,String place,String remark){
		super();
		this.id=id;
		this.uid=uid;
		this.money=money;
		this.time=time;	
		this.category=category;
		this.place=place;
		this.remark=remark;
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
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String toString(){
		return "编号："+this.id+"   支出金额："+this.money+"   时间："+this.time+"   类别："+this.category+"   地点："+this.place+"	   备注："+this.remark;
	}
}
