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
	        
	        // ��ӱ�ǩ
	        addView1 = findViewById(R.id.addtextView1);
			addView2 = findViewById(R.id.addtextView2);
			addText1 = findViewById(R.id.addeditText1);	
			addBtn1 = findViewById(R.id.addbutton1);
			addBtn2 = findViewById(R.id.addbutton2);
			
			// �޸ı�ǩ
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
	v1.setVisibility(View.VISIBLE);   //������ʾ
	v1.setVisibility(View.INVISIBLE); //���ز��벼�֣���ռ�ŵط���
	v1.setVisibility(View.GONE);      //���ز����벼�֣���ռ�ط���
*/	 
	 
/****************************��ӱ�ǩ����************************************/	 
	 /**
	  * �����ʾ��ǩ
	  * @param view
	  */
	 public void Noteadd(View view){
		 Btn1.setVisibility(View.GONE);
		 Btn2.setVisibility(View.GONE);
		 Btn3.setVisibility(View.GONE);		 
		 // ��ʾ���� ��ӱ�ǩ����		 
		 addView1.setVisibility(View.VISIBLE);
		 addView2.setVisibility(View.VISIBLE); 
		 addText1.setVisibility(View.VISIBLE);		
		 addBtn1.setVisibility(View.VISIBLE);
		 addBtn2.setVisibility(View.VISIBLE);			
		((EditText)addText1).setText("");		 
	 }
	 /**
	  * ����    ��ӱ�ǩ
	  * @param view
	  */
	 public void Saveadd(View view){		 
		 EditText text=(EditText)addText1;
		 String str=text.getText().toString().trim();
		 if(str.equals("")){
			 Toast.makeText(ActivityNote.this,"��ǩ���ݲ���Ϊ�գ�", Toast.LENGTH_SHORT).show();
		 }else{
			LicaiDB=new ActionDB(this);
			LicaiDB.addnote(this.uid, str);
			boolean flag = LicaiDB.findnote(this.uid, str);
			if(flag==true){
				Toast.makeText(ActivityNote.this,"��ӳɹ���", Toast.LENGTH_SHORT).show();				
				this.Noneadd(view);// ��ӳɹ�����
			}	 
		 }
	 }
	 /**
	  * ����    ��ӱ�ǩ    ��      ����
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

	 

	 
/****************************��ǩ�鿴����************************************/		 

	 /**
	  * �鿴��ǩ
	  * @param view
	  */
	 public void Noteselect(View view){
		 Btn1.setVisibility(View.GONE);
		 Btn2.setVisibility(View.GONE);
		 Btn3.setVisibility(View.GONE);
		 
		 seeBtn.setVisibility(View.VISIBLE); 
		 Lv.setVisibility(View.VISIBLE); 
		 
		 		 
		 // ��ȡ��ǩ��������
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

            // ��ȡ��ǰ��Ŀ�����ݶ���
			final Note a = list.get(position);            
			ID.setText(Integer.toString(position+1));
			Info.setText(a.getInfo());
				
			// ���ɾ����ť�����¼�
			Btn.setOnClickListener(new OnClickListener(){
				// ɾ����ǩ
				public void onClick(View v) {
					// �����Ի���
					android.content.DialogInterface.OnClickListener listener=new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							list.remove(a);  //  �Ӽ�����ɾ��
							LicaiDB.deletenote(Integer.toString(a.getId()));  // �����ݿ���ɾ��
							notifyDataSetChanged();  // ˢ�½���	
						}						
					};
					
					// �����Ի���
					Builder builder=new Builder(ActivityNote.this);
					builder.setTitle("ȷ��Ҫɾ����");// ���ñ���				
					builder.setPositiveButton("ȷ��", listener); // ȷ��ɾ��
					builder.setNegativeButton("ȡ��",null); // ȡ��ɾ��					
					builder.show();  // ��ʾ�Ի���
				}				
			});
			return item;
		}
	 }
	 
	//  �� Item  ��ӵ�������¼� 
	 private class MyonItemclickListener implements OnItemClickListener{		
		 @Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			//  ��ȡ��ǰλ������
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
	 // �޸ı�ǩ��Ϣ
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
	 
	 
	 
	 // �����޸ĵı�ǩ��Ϣ
	 public void saveupdatainfo(View view){		 
		 String info=((EditText) updataView3).getText().toString().trim();
		 if(info.equals("")){
			 Toast.makeText(ActivityNote.this,"��ǩ��Ϣ����Ϊ�գ�", Toast.LENGTH_SHORT).show();
		 }else{
			 int flag=LicaiDB.updatenote(noteid,info);
			 boolean flag1 = LicaiDB.findnote1(noteid, info);
			 if(flag1==true){
				 Toast.makeText(ActivityNote.this,"�޸ĳɹ���", Toast.LENGTH_SHORT).show();				
				 noteid=null;			 
				 updataView1.setVisibility(View.GONE);
				 updataView3.setVisibility(View.GONE);
				 updatabutton1.setVisibility(View.GONE);
				 updatabutton2.setVisibility(View.GONE);
				 this.Noteselect(view);
			 }  
		 } 
	 }
	 // ���޸ı�ǩ��Ϣ����
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
	  * ���ز��ǩ����
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
	  * ������ȡ������
	  * @param view
	  */
	 public void Return(View view){
		 Intent in=new Intent(this,Activity1.class);
		 in.putExtra("uid", this.uid);
		 startActivity(in);
	 } 
}