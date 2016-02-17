package com.example.licai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * 
 * ������ģ��
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
		 // �˳�ϵͳ
		 Intent in=new Intent(this,MainActivity.class);
		 startActivity(in);
	 }
	 public void Setup(View view){
		 // ϵͳ����
		 Intent in=new Intent(this,ActivitySetup.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void Outlay(View view){
		 // ����֧��
		 Intent in=new Intent(this,ActivityOutlay.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);		 
	 }
	 public void Income(View view){
		 // ��������
		 Intent in=new Intent(this,ActivityIncome.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);		 
	 }
	 public void MyOutlay(View view){
		// �ҵ�֧��
		 Intent in=new Intent(this,ActivityMyOutlay.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void MyIncome(View view){
		// �ҵ�����
		 Intent in=new Intent(this,ActivityMyIncome.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void ExportDB(View view){
		 // ���ݹ���
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
