package com.example.licai;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.db.ActionDB;
import com.example.object.Income;

public class ActivityIncome extends Activity{
	 private String uid;
	 private String category;  // 类别	 
	 private Spinner categorys = null;   // 定义下拉列表框
	 private Calendar c = null;
	 private EditText et=null; 
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_income);	        
	        Intent in=getIntent();	        
	        this.uid=in.getStringExtra("uid");	       
	        //Toast.makeText(ActivityIncome.this,this.uid, Toast.LENGTH_SHORT).show();
	        this.categorys = (Spinner) super.findViewById(R.id.category);    // 取得组件
	        this.categorys.setOnItemSelectedListener( new OnItemSelectedListenerImpl()); 
	        
	        et=(EditText)findViewById(R.id.editText2);	        
	        // 设置时间监听器 
	        et.setOnClickListener(new View.OnClickListener(){              
	             public void onClick(View v) {                  
	            	 onCreateDialog().show();              
	             }          
	        });
	 }
	 /**
	  * 
	  * 下拉框监听类别选择事件
	  * @author Administrator
	  *
	  */
	 private class OnItemSelectedListenerImpl implements OnItemSelectedListener {
		@Override
		/**
		 * 选项选中时触发 	
		 */
		public void onItemSelected(AdapterView<?>adapter,View view,int position,long id) {			
			//获取选择的项的值  
            category=adapter.getItemAtPosition(position).toString();  
            //Toast.makeText(getApplicationContext(), sInfo, Toast.LENGTH_LONG).show();
		}	
		@Override
		/**
		 * 没有选项时触发
		 */
		public void onNothingSelected(AdapterView<?> arg0) {
			 String sInfo="什么也没选！";  
	         Toast.makeText(getApplicationContext(),sInfo, Toast.LENGTH_LONG).show();			
		} 		 
	 }
	 /**
	  * 监听时间
	  * @param view
	  */
	 protected Dialog onCreateDialog() {          
        Dialog dialog = null;                                     
		    c = Calendar.getInstance();              
		    dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {  
		        public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {  
		            et.setText(year + "-" + (month+1) + "-" + dayOfMonth);   
		        }   
		    }, c.get(Calendar.YEAR), // 传入年份                
		    c.get(Calendar.MONTH), // 传入月份                 
		    c.get(Calendar.DAY_OF_MONTH) // 传入天数            
		    );              
        return dialog;      
    }
	 /**
	  * 保存新增收入
	  * @param view
	  */
	 
	 
	 
	 public void Saveinfo(View view){
		 String money = ((EditText)findViewById(R.id.editText1)).getText().toString().trim();
	     String time  = ((EditText)findViewById(R.id.editText2)).getText().toString().trim();
	     String pay = ((EditText)findViewById(R.id.editText3)).getText().toString().trim(); 
	     String remark = ((EditText)findViewById(R.id.editText4)).getText().toString().trim();
	     
	     if(money.equals("")||time.equals("")||pay.equals("")||remark.equals("")){
	    	 Toast.makeText(ActivityIncome.this,"请填写完信息！", Toast.LENGTH_SHORT).show();
	     }else{
	    	 if(isValidDate(time)==false){	    		 
	    		 Toast.makeText(ActivityIncome.this,"时间格式不正确！", Toast.LENGTH_SHORT).show();
	    	 }else{
	    		 ActionDB LicaiDB=new ActionDB(this);	    	 
			     LicaiDB.addincome(this.uid, money, time, this.category, pay, remark);		     
			     List<Income> Income=LicaiDB.findincome(this.uid, money, time, this.category, pay, remark);		    
			     if(Income.isEmpty()){
			    	 Toast.makeText(ActivityIncome.this,"添加失败！", Toast.LENGTH_SHORT).show();	    	
			     }else{
			    	 Toast.makeText(ActivityIncome.this,"添加成功！", Toast.LENGTH_SHORT).show();
			    	 Intent in=new Intent(this,Activity1.class);
					 in.putExtra("uid", this.uid);
					 startActivity(in);
			     }
	    	 }  
	     } 	 
	 }
	// 检测时间格式
	 @SuppressLint("SimpleDateFormat") public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       try {
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {	 
	           convertSuccess=false;
	       } catch (java.text.ParseException e) {			
	    	   convertSuccess=false;
		} 
	       return convertSuccess;
	}
	 
	 public void Return(View view){
		 // 新增收入   取消返回
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
}