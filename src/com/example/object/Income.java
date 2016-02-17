package com.example.object;

public class Income {
	private int id;
	private int uid;
	private String money;
	private String time;
	private String category;
	private String pay;
	private String remark;
	public Income(){
		super();
	}
	public Income(int id,int uid,String money,String time,String category,String pay,String remark){
		super();
		this.id=id;
		this.uid=uid;
		this.money=money;
		this.time=time;	
		this.category=category;
		this.pay=pay;
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
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String toString(){
		return "编号："+this.id+"   收入金额："+this.money+"   时间："+this.time+"   类别："+this.category+"   付款方："+this.pay+"	   备注："+this.remark;
	}
}
