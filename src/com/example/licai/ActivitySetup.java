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
 * 系统设置模块
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
	     	List<User> user = LicaiDB.finduserUid(this.uid);//通过  uid 查询用户名
	     	String name = user.get(0).getName();	     	
	     	EditText user_name = (EditText)findViewById(R.id.editText1);
	     	user_name.setText(name);	     		        
	        //Toast.makeText(ActivitySetup.this,this.uid, Toast.LENGTH_SHORT).show();
	 }
	 public void Return(View view){
		 // 系统设置   取消返回
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 public void Savepass(View view){
		 // 系统设置
		 EditText pass = (EditText)findViewById(R.id.editText2);
	     EditText pass1 = (EditText)findViewById(R.id.editText3);
	     String P1=pass.getText().toString().trim();
	     String P2=pass1.getText().toString().trim();
	     if(P1.equals("")||P2.equals("")){
	    	 Toast.makeText(ActivitySetup.this,"密码和确认密码不能为空！", Toast.LENGTH_SHORT).show();
	     }else if(!P1.equals(P2)){
	    	 Toast.makeText(ActivitySetup.this,"确认密码错误！", Toast.LENGTH_SHORT).show();
	     }else{
	    	 ActionDB LicaiDB=new ActionDB(this);
	    	 LicaiDB.updateuser(this.uid, P1);
	    	 
	    	 List<User> user = LicaiDB.finduserUid(this.uid);
	    	 if(user.get(0).getPass().equals(P1)){
	    		 Toast.makeText(ActivitySetup.this,"修改成功！", Toast.LENGTH_SHORT).show();
		    	 Intent in=new Intent(this,Activity1.class);
				 in.putExtra("uid", this.uid);
				 startActivity(in);
	    	 }else{
	    		 Toast.makeText(ActivitySetup.this,"修改失败！", Toast.LENGTH_SHORT).show();
	    	 }	    	 
	     } 
	 }	 
}
