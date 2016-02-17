package com.example.licai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * 
 * 主界面模块
 * @author Administrator
 *
 */
public class Activity1 extends Activity {
	 private String uid;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity1);
	        Intent in=getIntent();
	        this.uid=in.getStringExtra("uid");
	        //Toast.makeText(Activity1.this,this.uid, Toast.LENGTH_SHORT).show();
	 }
	 public void Return(View view){
		 // 退出系统
		 Intent in=new Intent(this,MainActivity.class);
		 startActivity(in);
	 }
	 public void Setup(View view){
		 // 系统设置
		 Intent in=new Intent(this,ActivitySetup.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void Outlay(View view){
		 // 新增支出
		 Intent in=new Intent(this,ActivityOutlay.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);		 
	 }
	 public void Income(View view){
		 // 新增收入
		 Intent in=new Intent(this,ActivityIncome.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);		 
	 }
	 public void MyOutlay(View view){
		// 我的支出
		 Intent in=new Intent(this,ActivityMyOutlay.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void MyIncome(View view){
		// 我的收入
		 Intent in=new Intent(this,ActivityMyIncome.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void ExportDB(View view){
		 // 数据管理
		 Intent in=new Intent(this,ActivityDb.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);		 
	 }
	 public void Note(View view){
		 Intent in=new Intent(this,ActivityNote.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
}
