package com.example.licai;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.ActionDB;
import com.example.object.Note;

public class ActivityNote extends Activity{
	 private String uid;
	 private String noteid=null;
	 private ActionDB LicaiDB=null;
	 private List<Note> list;
	 private View Btn1=null;
	 private View Btn2=null;
	 private View Btn3=null;
	 private View addView1=null;
	 private View addView2=null;
	 private View addText1=null;
	 private View addBtn1=null;
	 private View addBtn2=null;
	 private View seeBtn=null;
	 private ListView Lv=null;
	 private View updataView1=null;
	 private View updataView2=null;
	 private View updataView3=null;
	 private View updatabutton1=null;
	 private View updatabutton2=null;	
	 private View updatabutton3=null;
	 private MyAdapter adapter=null;

	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_note);
	        Intent in=getIntent();
	        this.uid=in.getStringExtra("uid");
	        //Toast.makeText(ActivityNote.this,this.uid, Toast.LENGTH_SHORT).show();	        
	        Btn1 = findViewById(R.id.button1);
	        Btn2 = findViewById(R.id.button2);
	        Btn3 = findViewById(R.id.button3);
	        
	        // 添加便签
	        addView1 = findViewById(R.id.addtextView1);
			addView2 = findViewById(R.id.addtextView2);
			addText1 = findViewById(R.id.addeditText1);	
			addBtn1 = findViewById(R.id.addbutton1);
			addBtn2 = findViewById(R.id.addbutton2);
			
			// 修改便签
			updataView1=findViewById(R.id.updataView1);
			updataView2=findViewById(R.id.updataView2);
			updataView3=findViewById(R.id.updataView3);
			updatabutton1=findViewById(R.id.updatabutton1);
			updatabutton2=findViewById(R.id.updtabutton2);
			updatabutton3=findViewById(R.id.updtabutton3);
			
			seeBtn = findViewById(R.id.Seebutton);
			Lv = (ListView) findViewById(R.id.listView1);			
	 }
/*	
	v1.setVisibility(View.VISIBLE);   //正常显示
	v1.setVisibility(View.INVISIBLE); //隐藏参与布局（还占着地方）
	v1.setVisibility(View.GONE);      //隐藏不参与布局（不占地方）
*/	 
	 
/****************************添加便签区域************************************/	 
	 /**
	  * 添加显示便签
	  * @param view
	  */
	 public void Noteadd(View view){
		 Btn1.setVisibility(View.GONE);
		 Btn2.setVisibility(View.GONE);
		 Btn3.setVisibility(View.GONE);		 
		 // 显示隐藏 添加便签区域		 
		 addView1.setVisibility(View.VISIBLE);
		 addView2.setVisibility(View.VISIBLE); 
		 addText1.setVisibility(View.VISIBLE);		
		 addBtn1.setVisibility(View.VISIBLE);
		 addBtn2.setVisibility(View.VISIBLE);			
		((EditText)addText1).setText("");		 
	 }
	 /**
	  * 保存    添加便签
	  * @param view
	  */
	 public void Saveadd(View view){		 
		 EditText text=(EditText)addText1;
		 String str=text.getText().toString().trim();
		 if(str.equals("")){
			 Toast.makeText(ActivityNote.this,"便签内容不能为空！", Toast.LENGTH_SHORT).show();
		 }else{
			LicaiDB=new ActionDB(this);
			LicaiDB.addnote(this.uid, str);
			boolean flag = LicaiDB.findnote(this.uid, str);
			if(flag==true){
				Toast.makeText(ActivityNote.this,"添加成功！", Toast.LENGTH_SHORT).show();				
				this.Noneadd(view);// 添加成功隐藏
			}	 
		 }
	 }
	 /**
	  * 隐藏    添加便签    区      返回
	  * @param view
	  */
	 public void Noneadd(View view){
		 addView1.setVisibility(View.GONE);
		 addView2.setVisibility(View.GONE); 
		 addText1.setVisibility(View.GONE);		
		 addBtn1.setVisibility(View.GONE);
		 addBtn2.setVisibility(View.GONE);
		 Btn1.setVisibility(View.VISIBLE);
		 Btn2.setVisibility(View.VISIBLE);
		 Btn3.setVisibility(View.VISIBLE); 
	 }
	 
/************************************************************************/

	 

	 
/****************************便签查看区域************************************/		 

	 /**
	  * 查看便签
	  * @param view
	  */
	 public void Noteselect(View view){
		 Btn1.setVisibility(View.GONE);
		 Btn2.setVisibility(View.GONE);
		 Btn3.setVisibility(View.GONE);
		 
		 seeBtn.setVisibility(View.VISIBLE); 
		 Lv.setVisibility(View.VISIBLE); 
		 
		 		 
		 // 获取便签所有数据
		 LicaiDB=new ActionDB(this);		 
		 list=LicaiDB.findnoteUid(uid);
		 adapter=new MyAdapter();
		 Lv.setAdapter(adapter); 
		 Lv.setOnItemClickListener(new MyonItemclickListener());
	 }
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
			View item=View.inflate(ActivityNote.this, R.layout.noteitem, null);			
			TextView ID=(TextView) item.findViewById(R.id.itemView1);
			TextView Info=(TextView) item.findViewById(R.id.itemView2);	
			Button  Btn=(Button) item.findViewById(R.id.itembutton);

            // 获取当前条目的数据对象
			final Note a = list.get(position);            
			ID.setText(Integer.toString(position+1));
			Info.setText(a.getInfo());
				
			// 点击删除按钮触发事件
			Btn.setOnClickListener(new OnClickListener(){
				// 删除便签
				public void onClick(View v) {
					// 弹出对话框
					android.content.DialogInterface.OnClickListener listener=new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							list.remove(a);  //  从集合中删除
							LicaiDB.deletenote(Integer.toString(a.getId()));  // 从数据库中删除
							notifyDataSetChanged();  // 刷新界面	
						}						
					};
					
					// 创建对话框
					Builder builder=new Builder(ActivityNote.this);
					builder.setTitle("确认要删除吗？");// 设置标题				
					builder.setPositiveButton("确认", listener); // 确认删除
					builder.setNegativeButton("取消",null); // 取消删除					
					builder.show();  // 显示对话框
				}				
			});
			return item;
		}
	 }
	 
	//  给 Item  添加点击监听事件 
	 private class MyonItemclickListener implements OnItemClickListener{		
		 @Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			//  获取当前位置数据
			Note a=(Note) parent.getItemAtPosition(position);
			seeBtn.setVisibility(View.GONE); 
			Lv.setVisibility(View.GONE);			
			((TextView) updataView2).setText(a.getInfo());
			((EditText) updataView3).setText(a.getInfo());			
			noteid=Integer.toString(a.getId());			
			updataView1.setVisibility(View.VISIBLE);
			updataView2.setVisibility(View.VISIBLE);
			updatabutton1.setVisibility(View.VISIBLE);
			updatabutton3.setVisibility(View.VISIBLE);
			
			
		}				 
	 }
	 // 修改便签信息
	 public void updatainfo(View view){		 
		 updataView2.setVisibility(View.GONE);
		 updataView3.setVisibility(View.VISIBLE);
		 updatabutton3.setVisibility(View.GONE);
		 updatabutton2.setVisibility(View.VISIBLE);
		 
		 updataView3.setFocusable(true);
		 updataView3.setFocusableInTouchMode(true);
		 updataView3.requestFocus();
		 updataView3.requestFocusFromTouch();		 
	 }
	 
	 
	 
	 // 保存修改的便签信息
	 public void saveupdatainfo(View view){		 
		 String info=((EditText) updataView3).getText().toString().trim();
		 if(info.equals("")){
			 Toast.makeText(ActivityNote.this,"便签信息不能为空！", Toast.LENGTH_SHORT).show();
		 }else{
			 int flag=LicaiDB.updatenote(noteid,info);
			 boolean flag1 = LicaiDB.findnote1(noteid, info);
			 if(flag1==true){
				 Toast.makeText(ActivityNote.this,"修改成功！", Toast.LENGTH_SHORT).show();				
				 noteid=null;			 
				 updataView1.setVisibility(View.GONE);
				 updataView3.setVisibility(View.GONE);
				 updatabutton1.setVisibility(View.GONE);
				 updatabutton2.setVisibility(View.GONE);
				 this.Noteselect(view);
			 }  
		 } 
	 }
	 // 不修改便签信息返回
	 public void returnupdatainfo(View view){
		 updataView1.setVisibility(View.GONE);
		 updataView2.setVisibility(View.GONE);
		 updataView3.setVisibility(View.GONE);
		 updatabutton1.setVisibility(View.GONE);
		 updatabutton3.setVisibility(View.GONE);
		 seeBtn.setVisibility(View.VISIBLE); 
		 Lv.setVisibility(View.VISIBLE);
	 }
	 /**
	  * 隐藏查便签看区
	  * @param view
	  */
	 public void NoteseeR(View view){
		 seeBtn.setVisibility(View.GONE); 
		 Lv.setVisibility(View.GONE);
		 Btn1.setVisibility(View.VISIBLE);
		 Btn2.setVisibility(View.VISIBLE);
		 Btn3.setVisibility(View.VISIBLE); 
	 }
	 
	 
/************************************************************************/	 
	 
	 
	 
	/**
	  * 主界面取消返回
	  * @param view
	  */
	 public void Return(View view){
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 } 
}