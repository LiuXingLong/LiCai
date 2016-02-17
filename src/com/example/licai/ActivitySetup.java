package com.example.licai;

import java.util.List;

import com.example.db.ActionDB;
import com.example.object.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * ϵͳ����ģ��
 * @author Administrator
 * 
 */
public class ActivitySetup extends Activity{
	 private String uid;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_setup);	        
	        Intent in=getIntent();	        
	        this.uid=in.getStringExtra("uid");	        
	        ActionDB LicaiDB=new ActionDB(this);       	       	
	     	List<User> user = LicaiDB.finduserUid(this.uid);//ͨ��  uid ��ѯ�û���
	     	String name = user.get(0).getName();	     	
	     	EditText user_name = (EditText)findViewById(R.id.editText1);
	     	user_name.setText(name);	     		        
	        //Toast.makeText(ActivitySetup.this,this.uid, Toast.LENGTH_SHORT).show();
	 }
	 public void Return(View view){
		 // ϵͳ����   ȡ������
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void Savepass(View view){
		 // ϵͳ����
		 EditText pass = (EditText)findViewById(R.id.editText2);
	     EditText pass1 = (EditText)findViewById(R.id.editText3);
	     String P1=pass.getText().toString().trim();
	     String P2=pass1.getText().toString().trim();
	     if(P1.equals("")||P2.equals("")){
	    	 Toast.makeText(ActivitySetup.this,"�����ȷ�����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
	     }else if(!P1.equals(P2)){
	    	 Toast.makeText(ActivitySetup.this,"ȷ���������", Toast.LENGTH_SHORT).show();
	     }else{
	    	 ActionDB LicaiDB=new ActionDB(this);
	    	 LicaiDB.updateuser(this.uid, P1);
	    	 
	    	 List<User> user = LicaiDB.finduserUid(this.uid);
	    	 if(user.get(0).getPass().equals(P1)){
	    		 Toast.makeText(ActivitySetup.this,"�޸ĳɹ���", Toast.LENGTH_SHORT).show();
		    	 Intent in=new Intent(this,Activity1.class);
				 in.putExtra("uid", this.uid);
				 startActivity(in);
	    	 }else{
	    		 Toast.makeText(ActivitySetup.this,"�޸�ʧ�ܣ�", Toast.LENGTH_SHORT).show();
	    	 }	    	 
	     } 
	 }	 
}
