package com.example.licai;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db.ActionDB;
import com.example.object.Income;
import com.example.object.Outlay;

public class ActivityDb extends Activity{
	 private String uid;
	 private ActionDB LicaiDB=null;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_db);
	        Intent in=getIntent();
	        this.uid=in.getStringExtra("uid");
	        //Toast.makeText(ActivityDb.this,this.uid, Toast.LENGTH_SHORT).show();
	 }
	 // ����֧������
	 public void export_out(View view){
		 LicaiDB=new ActionDB(this);
		 List<Outlay> Exportout=LicaiDB.findoutlayUid(this.uid);
		 deleteFile("out.txt");
		 for(int i = 0;i<Exportout.size();i++){
			 String str=Exportout.get(i).toString();
			 if(i!=Exportout.size()-1){
				 str=str+"\r\n";
			 }
			 //Toast.makeText(ActivityDb.this,str, Toast.LENGTH_SHORT).show();
			 this.export_data("out.txt", str);	 
		 }
		 Toast.makeText(ActivityDb.this,"֧�����ݵ�����ɣ�", Toast.LENGTH_SHORT).show();
	 }
	 // ������������
	 public void export_in(View view){
		 LicaiDB=new ActionDB(this);
		 List<Income> Exportin=LicaiDB.findincomeUid(this.uid);
		 deleteFile("in.txt");
		 for(int i = 0;i<Exportin.size();i++){
			 String str=Exportin.get(i).toString();
			 if(i!=Exportin.size()-1){
				 str=str+"\r\n";
			 }
			 //Toast.makeText(ActivityDb.this,str, Toast.LENGTH_SHORT).show();
			 this.export_data("in.txt", str); 
		 }
		 Toast.makeText(ActivityDb.this,"�������ݵ�����ɣ�", Toast.LENGTH_SHORT).show();
	 }
	 public void Return(View view){
		 // ����
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
	 //  д���ļ�
	 public void export_data(String fileName,String content){		  
		FileOutputStream fos;		
		try {
			fos=openFileOutput(fileName, MODE_APPEND);//    MODE_APPEND  ��׷��        MODE_PRIVATE ֻ����ǰӦ�ó����д
			fos.write(content.getBytes());
			fos.close();			
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {		
				e.printStackTrace();
		}	
	}
}