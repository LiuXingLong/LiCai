package com.example.licai;

import java.util.List;

import com.example.db.ActionDB;
import com.example.object.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * 登入注册模块
 * @author Administrator
 *
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    // 注册
    public void Regist(View view){
    	Intent in=new Intent(this,Activity1.class);     	
    	EditText username = (EditText)findViewById(R.id.EditText01);
    	EditText password = (EditText)findViewById(R.id.editText1);
    	String name=username.getText().toString().trim();
    	String pass=password.getText().toString().trim();
    	if(name.equals("")||pass.equals("")){
    		Toast.makeText(MainActivity.this,"用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
    	}else{
    		ActionDB LicaiDB=new ActionDB(this);       	       	
        	List<User> user = LicaiDB.finduser(name);// 查询用户是否已注册过
        	if(user.isEmpty()){
        		// 没有注册过添加注册  	
            	LicaiDB.adduser(name,pass);       	
            	user = LicaiDB.finduser(name, pass);
            	String uid = String.valueOf(user.get(0).getUid());            	
            	in.putExtra("uid", uid);
            	startActivity(in);       	
        	}else{
        		// 提示错误
        		Toast.makeText(MainActivity.this,"抱歉！该用户名已经注册过了！", Toast.LENGTH_SHORT).show();
        	}		
    	}	
    }
    // 登入
    public void Login(View view){
    	Intent in=new Intent(this,Activity1.class);     	
    	EditText username = (EditText)findViewById(R.id.EditText01);
    	EditText password = (EditText)findViewById(R.id.editText1);
    	String name=username.getText().toString().trim();
    	String pass=password.getText().toString().trim();
    	if(name.equals("")||pass.equals("")){
    		Toast.makeText(MainActivity.this,"用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
    	}else{
    		ActionDB LicaiDB=new ActionDB(this);       	       	
        	List<User> user = LicaiDB.finduser(name,pass);// 查询用户名或密码是否正确
        	if(user.isEmpty()){
        		// 提示错误
        		Toast.makeText(MainActivity.this,"用户名或密码错误！", Toast.LENGTH_SHORT).show();      
        	}else{       		
        		String uid = String.valueOf(user.get(0).getUid());
            	in.putExtra("uid", uid);
            	startActivity(in);
        	}		
    	}	
    }  
}