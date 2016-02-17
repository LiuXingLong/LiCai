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
 * ����ע��ģ��
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
    // ע��
    public void Regist(View view){
    	Intent in=new Intent(this,Activity1.class);     	
    	EditText username = (EditText)findViewById(R.id.EditText01);
    	EditText password = (EditText)findViewById(R.id.editText1);
    	String name=username.getText().toString().trim();
    	String pass=password.getText().toString().trim();
    	if(name.equals("")||pass.equals("")){
    		Toast.makeText(MainActivity.this,"�û��������벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
    	}else{
    		ActionDB LicaiDB=new ActionDB(this);       	       	
        	List<User> user = LicaiDB.finduser(name);// ��ѯ�û��Ƿ���ע���
        	if(user.isEmpty()){
        		// û��ע������ע��  	
            	LicaiDB.adduser(name,pass);       	
            	user = LicaiDB.finduser(name, pass);
            	String uid = String.valueOf(user.get(0).getUid());            	
            	in.putExtra("uid", uid);
            	startActivity(in);       	
        	}else{
        		// ��ʾ����
        		Toast.makeText(MainActivity.this,"��Ǹ�����û����Ѿ�ע����ˣ�", Toast.LENGTH_SHORT).show();
        	}		
    	}	
    }
    // ����
    public void Login(View view){
    	Intent in=new Intent(this,Activity1.class);     	
    	EditText username = (EditText)findViewById(R.id.EditText01);
    	EditText password = (EditText)findViewById(R.id.editText1);
    	String name=username.getText().toString().trim();
    	String pass=password.getText().toString().trim();
    	if(name.equals("")||pass.equals("")){
    		Toast.makeText(MainActivity.this,"�û��������벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
    	}else{
    		ActionDB LicaiDB=new ActionDB(this);       	       	
        	List<User> user = LicaiDB.finduser(name,pass);// ��ѯ�û����������Ƿ���ȷ
        	if(user.isEmpty()){
        		// ��ʾ����
        		Toast.makeText(MainActivity.this,"�û������������", Toast.LENGTH_SHORT).show();      
        	}else{       		
        		String uid = String.valueOf(user.get(0).getUid());
            	in.putExtra("uid", uid);
            	startActivity(in);
        	}		
    	}	
    }  
}