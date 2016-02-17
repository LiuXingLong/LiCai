package com.example.licai;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.ActionDB;
import com.example.object.Outlay;


public class ActivityMyOutlay extends Activity{
	 private String uid;
	 private ActionDB LicaiDB=null;
	 private List<Outlay> list;
	 private ListView Lv=null;
	 private MyAdapter adapter=null;
	 private Calendar c = null;
	 	 
	 private View button2=null;
	 private View button3=null;
	 private View textView1=null;
	 
	 private View showView1=null;
	 private View showView2=null;
	 private View showView3=null;
	 private View showView4=null;
	 private View showView5=null;
	 private View showView6=null;
	 private View showView7=null;
	 private View showView8=null;
	 private View showView9=null;
	 private View showView10=null;
	 private View showView11=null;
	 private View showbutton1=null;
	 
	 private View TView1=null;
	 private View TView2=null;
	 private View TView3=null;
	 private View TView4=null;
	 private View TView5=null;	 
	 private View spinner1=null;
	 private View spinner2=null;
	 private View Tbutton1=null;
	 private View Tbutton2=null;
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_myoutlay);
	        Intent in=getIntent();
	        this.uid=in.getStringExtra("uid");
	        //Toast.makeText(ActivityMyOutlay.this,this.uid, Toast.LENGTH_SHORT).show();
	        	        
	        button2=findViewById(R.id.button2);
	        button3=findViewById(R.id.button3);
	        textView1=findViewById(R.id.textView1);
	        
	        showView1=findViewById(R.id.showView1);
	        showView2=findViewById(R.id.showView2);
	        showView3=findViewById(R.id.showView3);
	        showView4=findViewById(R.id.showView4);
	        showView5=findViewById(R.id.showView5);
	        showView6=findViewById(R.id.showView6);
	        showView7=findViewById(R.id.showView7);
	        showView8=findViewById(R.id.showView8);
	        showView9=findViewById(R.id.showView9);
	        showView10=findViewById(R.id.showView10);
	        showView11=findViewById(R.id.showView11);
	        showbutton1=findViewById(R.id.showbutton1);
	        
	        TView1=findViewById(R.id.TView1);
	   	    TView2=findViewById(R.id.TView2);
	   	    TView3=findViewById(R.id.TView3);
	   	    TView4=findViewById(R.id.TView4);
	   	    TView5=findViewById(R.id.TView5);	 
	   	    spinner1=findViewById(R.id.spinner1);
	   	    spinner2=findViewById(R.id.spinner2);
	   	    Tbutton1=findViewById(R.id.Tbutton1);
	   	    Tbutton2=findViewById(R.id.Tbutton2);
	              
	   	    /*********设置时间监听器 *********/
	   	    //开始
	   	    spinner1.setOnClickListener(new View.OnClickListener(){              
	             public void onClick(View v) {                  
	            	 onCreateDialog(1).show();              
	             }          
	        });
		 	//结束
		   	spinner2.setOnClickListener(new View.OnClickListener(){              
	             public void onClick(View v) {                  
	            	 onCreateDialog(2).show();              
	             }          
	        });
		   	
	        this.See(null);      
	 }
	 public void See(View view){
		 LicaiDB=new ActionDB(this);		 
	     list=LicaiDB.findoutlayUid(uid);
	     adapter=new MyAdapter();
	     Lv = (ListView) findViewById(R.id.listView1);
	     Lv.setAdapter(adapter);
	     Lv.setOnItemClickListener(new MyonItemclickListener());
	 }
	 
	 /*	
		v1.setVisibility(View.VISIBLE);   //正常显示
		v1.setVisibility(View.INVISIBLE); //隐藏参与布局（还占着地方）
		v1.setVisibility(View.GONE);      //隐藏不参与布局（不占地方）
	*/	

/**************************** 查看区*******************************/
	 
	 // 适配器
	 private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {			
			return list.get(position);
		}
		@Override
		public long getItemId(int position) {			
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {			
			View item=View.inflate(ActivityMyOutlay.this, R.layout.item, null);	
			
			TextView ID=(TextView) item.findViewById(R.id.ID);
			TextView Info=(TextView) item.findViewById(R.id.Info);	
			TextView Time=(TextView) item.findViewById(R.id.Time);

			// 获取当前条目的数据对象
			final Outlay a = list.get(position);          
			ID.setText(Integer.toString(position+1));
			Info.setText(a.getCategory()+":"+a.getMoney()+"元");
			Time.setText(a.getTime());
			return item;
		}
	 }
	//  给 Item  添加点击监听事件 
	 private class MyonItemclickListener implements OnItemClickListener{		
		 @Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			//  获取当前位置数据
			 Outlay a=(Outlay) parent.getItemAtPosition(position);
			 
			 Lv.setVisibility(View.GONE); 			 
	         button2.setVisibility(View.GONE); 
	         button3.setVisibility(View.GONE); 
	         textView1.setVisibility(View.GONE); 
			 	        
		     ((TextView) showView3).setText(a.getMoney());		    
		     ((TextView) showView5).setText(a.getTime());	    
		     ((TextView) showView7).setText(a.getCategory());       	
	         ((TextView) showView9).setText(a.getPlace());      
	         ((TextView) showView11).setText(a.getRemark());
	         
			 showView1.setVisibility(View.VISIBLE);
		     showView2.setVisibility(View.VISIBLE);
		     showView3.setVisibility(View.VISIBLE);
		     showView4.setVisibility(View.VISIBLE);
		     showView5.setVisibility(View.VISIBLE);
		     showView6.setVisibility(View.VISIBLE);
		     showView7.setVisibility(View.VISIBLE);
	       	 showView8.setVisibility(View.VISIBLE);
	         showView9.setVisibility(View.VISIBLE);
	         showView10.setVisibility(View.VISIBLE);
	         showView11.setVisibility(View.VISIBLE);
	         showbutton1.setVisibility(View.VISIBLE);
		}				 
	 }
	 public void ShowReturn(View view){
		 showView1.setVisibility(View.GONE);
	     showView2.setVisibility(View.GONE);
	     showView3.setVisibility(View.GONE);
	     showView4.setVisibility(View.GONE);
	     showView5.setVisibility(View.GONE);
	     showView6.setVisibility(View.GONE);
	     showView7.setVisibility(View.GONE);
       	 showView8.setVisibility(View.GONE);
         showView9.setVisibility(View.GONE);
         showView10.setVisibility(View.GONE);
         showView11.setVisibility(View.GONE);
         showbutton1.setVisibility(View.GONE);
         
         Lv.setVisibility(View.VISIBLE); 		 
         button2.setVisibility(View.VISIBLE); 
         button3.setVisibility(View.VISIBLE); 
         textView1.setVisibility(View.VISIBLE);
	 }
	 
/****************************************************************/	 
	 
	 
/**************************** 统计区*******************************/	
	 
	 
	public void TJ(View view){
		((TextView) spinner1).setText("");
		((TextView) spinner2).setText("");
		((TextView) TView5).setText("");
		
		Lv.setVisibility(View.GONE); 			 
        button2.setVisibility(View.GONE); 
        button3.setVisibility(View.GONE); 
        textView1.setVisibility(View.GONE);
		
        TView1.setVisibility(View.VISIBLE);
   	    TView2.setVisibility(View.VISIBLE);
   	    TView3.setVisibility(View.VISIBLE);
   	    TView4.setVisibility(View.VISIBLE);
   	    TView5.setVisibility(View.VISIBLE);	 
   	    spinner1.setVisibility(View.VISIBLE);
   	    spinner2.setVisibility(View.VISIBLE);
   	    Tbutton1.setVisibility(View.VISIBLE);
   	    Tbutton2.setVisibility(View.VISIBLE);		
	}
	public void Treturn(View view){
		TView1.setVisibility(View.GONE);
   	    TView2.setVisibility(View.GONE);
   	    TView3.setVisibility(View.GONE);
   	    TView4.setVisibility(View.GONE);
   	    TView5.setVisibility(View.GONE);	 
   	    spinner1.setVisibility(View.GONE);
   	    spinner2.setVisibility(View.GONE);
   	    Tbutton1.setVisibility(View.GONE);
   	    Tbutton2.setVisibility(View.GONE);
	   	    
		Lv.setVisibility(View.VISIBLE); 			 
        button2.setVisibility(View.VISIBLE); 
        button3.setVisibility(View.VISIBLE); 
        textView1.setVisibility(View.VISIBLE); 		
	}
	protected Dialog onCreateDialog(final int flag){          
        Dialog dialog = null;                                     
		    c = Calendar.getInstance();              
		    dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {  
		        public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
		        	if(flag==1){
		        		((TextView) spinner1).setText(year + "-" + (month+1) + "-" + dayOfMonth);
		        	}else if(flag==2){
		        		((TextView) spinner2).setText(year + "-" + (month+1) + "-" + dayOfMonth);
		        	}		        	   
		        }   
		    }, c.get(Calendar.YEAR), // 传入年份                
		    c.get(Calendar.MONTH), // 传入月份                 
		    c.get(Calendar.DAY_OF_MONTH) // 传入天数            
		    );              
        return dialog;      
    }
	public void Jisuan(View view){
		int T,T1,T2,Sum=0;
		String[] str=new String[3];
		String time1=((TextView) spinner1).getText().toString().trim();
		String time2=((TextView) spinner2).getText().toString().trim();		
		if(time1.equals("")||time2.equals("")){
			Toast.makeText(ActivityMyOutlay.this,"起始时间和结束时间不能为空！", Toast.LENGTH_SHORT).show();
		}else{
			str=time1.split("-");		
			T1=Integer.parseInt(str[0]+str[1]+str[2]);			
			str=time2.split("-");
			T2=Integer.parseInt(str[0]+str[1]+str[2]);
			if(T1>T2){
				Toast.makeText(ActivityMyOutlay.this,"起始时间大于结束时间。", Toast.LENGTH_SHORT).show();				
			}else{
				for(int i=0;i<list.size();i++){
					str=((list.get(i)).getTime()).split("-");
					T=Integer.parseInt(str[0]+str[1]+str[2]);
					if(T>=T1&&T<=T2){
						Sum+=Integer.parseInt((list.get(i)).getMoney());
					}
				}			
				((TextView) TView5).setText(String.valueOf(Sum)+"元");
			}	
		}	
	}
	 
	 
/****************************************************************/
	 
	 
	 // 返回
	 public void Return(View view){		 
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 } 
}
