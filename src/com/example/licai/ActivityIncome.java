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
	 private String category;  // ���	 
	 private Spinner categorys = null;   // ���������б��
	 private Calendar c = null;
	 private EditText et=null; 
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_income);	        
	        Intent in=getIntent();	        
	        this.uid=in.getStringExtra("uid");	       
	        //Toast.makeText(ActivityIncome.this,this.uid, Toast.LENGTH_SHORT).show();
	        this.categorys = (Spinner) super.findViewById(R.id.category);    // ȡ�����
	        this.categorys.setOnItemSelectedListener( new OnItemSelectedListenerImpl()); 
	        
	        et=(EditText)findViewById(R.id.editText2);	        
	        // ����ʱ������� 
	        et.setOnClickListener(new View.OnClickListener(){              
	             public void onClick(View v) {                  
	            	 onCreateDialog().show();              
	             }          
	        });
	 }
	 /**
	  * 
	  * ������������ѡ���¼�
	  * @author Administrator
	  *
	  */
	 private class OnItemSelectedListenerImpl implements OnItemSelectedListener {
		@Override
		/**
		 * ѡ��ѡ��ʱ���� 	
		 */
		public void onItemSelected(AdapterView<?>adapter,View view,int position,long id) {			
			//��ȡѡ������ֵ  
            category=adapter.getItemAtPosition(position).toString();  
            //Toast.makeText(getApplicationContext(), sInfo, Toast.LENGTH_LONG).show();
		}	
		@Override
		/**
		 * û��ѡ��ʱ����
		 */
		public void onNothingSelected(AdapterView<?> arg0) {
			 String sInfo="ʲôҲûѡ��";  
	         Toast.makeText(getApplicationContext(),sInfo, Toast.LENGTH_LONG).show();			
		} 		 
	 }
	 /**
	  * ����ʱ��
	  * @param view
	  */
	 protected Dialog onCreateDialog() {          
        Dialog dialog = null;                                     
		    c = Calendar.getInstance();              
		    dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {  
		        public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {  
		            et.setText(year + "-" + (month+1) + "-" + dayOfMonth);   
		        }   
		    }, c.get(Calendar.YEAR), // �������                
		    c.get(Calendar.MONTH), // �����·�                 
		    c.get(Calendar.DAY_OF_MONTH) // ��������            
		    );              
        return dialog;      
    }
	 /**
	  * ������������
	  * @param view
	  */
	 
	 
	 
	 public void Saveinfo(View view){
		 String money = ((EditText)findViewById(R.id.editText1)).getText().toString().trim();
	     String time  = ((EditText)findViewById(R.id.editText2)).getText().toString().trim();
	     String pay = ((EditText)findViewById(R.id.editText3)).getText().toString().trim(); 
	     String remark = ((EditText)findViewById(R.id.editText4)).getText().toString().trim();
	     
	     if(money.equals("")||time.equals("")||pay.equals("")||remark.equals("")){
	    	 Toast.makeText(ActivityIncome.this,"����д����Ϣ��", Toast.LENGTH_SHORT).show();
	     }else{
	    	 if(isValidDate(time)==false){	    		 
	    		 Toast.makeText(ActivityIncome.this,"ʱ���ʽ����ȷ��", Toast.LENGTH_SHORT).show();
	    	 }else{
	    		 ActionDB LicaiDB=new ActionDB(this);	    	 
			     LicaiDB.addincome(this.uid, money, time, this.category, pay, remark);		     
			     List<Income> Income=LicaiDB.findincome(this.uid, money, time, this.category, pay, remark);		    
			     if(Income.isEmpty()){
			    	 Toast.makeText(ActivityIncome.this,"���ʧ�ܣ�", Toast.LENGTH_SHORT).show();	    	
			     }else{
			    	 Toast.makeText(ActivityIncome.this,"��ӳɹ���", Toast.LENGTH_SHORT).show();
			    	 Intent in=new Intent(this,Activity1.class);
					 in.putExtra("uid", this.uid);
					 startActivity(in);
			     }
	    	 }  
	     } 	 
	 }
	// ���ʱ���ʽ
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
		 // ��������   ȡ������
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 }
}