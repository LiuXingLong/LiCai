package com.example.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.object.Income;
import com.example.object.Note;
import com.example.object.Outlay;
import com.example.object.User;

/**
 * ���ݿ����ģ��
 * @author Administrator
 *
 */
public class ActionDB {
		private LicaiSQLiteOpenHelper helper;
		public ActionDB(Context context){
			helper=new LicaiSQLiteOpenHelper(context);
		}
		// ����û�
		public long adduser(String name,String pass){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("name", name);
			values.put("pass", pass);
			long flag=db.insert("user",null,values);
			db.close();
			return flag;
		}
		// ɾ���û�
		public int deleteuser(String uid){
			SQLiteDatabase db=helper.getWritableDatabase();	
			int flag=db.delete("user","uid=?",new String[]{uid});
			db.close();
			return flag;
		}
		// �޸��û�
		public int updateuser(String uid,String name,String pass){
			// ��ȡһ���ɶ�д�� SQLiteDatabase
			SQLiteDatabase db=helper.getWritableDatabase();
			 //����һ�� ContentValueszc   ����
			ContentValues values=new ContentValues();
			//��������  Key��value ����ʽ��ӽ�ȥ
			values.put("name",name);
			values.put("pass",pass);
			// ִ���޸ķ���
			int flag=db.update("user", values, "uid=?", new String[]{uid});
			// �ر����ݿ�
			db.close();
			return flag;
		}
		// �޸��û�����
		public void updateuser(String uid,String pass){
			// ��ȡһ���ɶ�д�� SQLiteDatabase
			SQLiteDatabase db=helper.getWritableDatabase();
			 //����һ�� ContentValueszc   ����
			ContentValues values=new ContentValues();
			//��������  Key��value ����ʽ��ӽ�ȥ			
			values.put("pass",pass);
			// ִ���޸ķ���			
			db.update("user", values, "uid=?", new String[]{uid});			
			//db.execSQL("update user set pass=? where uid=?", new Object[]{pass, uid});
			
			// �ر����ݿ�
			db.close();
			//return flag;
		}
		// ��ѯ�û�
		public List<User> finduser(String name,String pass){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "name=? and pass=?", new String[]{name,pass}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}		
		// ��ѯ�û����Ƿ����
		public List<User> finduser(String name){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "name=?", new String[]{name}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}
		// ͨ�� uid ��ѯ�û���Ϣ
		public List<User> finduserUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "uid=?", new String[]{uid}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}
				
		
		
		
		
		
		// �������
		public long addincome(String uid,String money,String time,String category,String pay,String remark){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("uid", uid);
			values.put("money", money);
			values.put("time", time);
			values.put("category", category);
			values.put("pay", pay);
			values.put("remark", remark);
			long flag=db.insert("income",null,values);
			db.close();
			return flag;
		}
		// ɾ������
		public long deleteincome(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("income","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// �޸�����
		public int updateincome(String id,String money,String time,String category,String pay,String remark){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("id", id);
			values.put("money", money);
			values.put("time", time);
			values.put("category", category);
			values.put("pay", pay);
			values.put("remark", remark);
			int flag=db.update("income", values, "id=?", new String[]{id});
			db.close();
			return flag;
		}
		// ��ѯ����
		public boolean findincome(String id){
			// ��ȡ�ɶ������ݿ�
			SQLiteDatabase db=helper.getReadableDatabase();
			// ��ѯ���ݿ���� �� ��������ѯ��������ѯ��������ѯ����ֵ������������having����������ʽ
			Cursor cursor=db.query("income", null, "id=?", new String[]{id}, null,null,null);
			// �Ƿ�����һ��ֵ
			boolean result=cursor.moveToNext();
			// �ر��α�
			cursor.close();
			// �ر����ݿ�
			db.close();
			return result;
		}
		// ��ѯ����
		public List<Income> findincome(String uid,String money,String time,String category,String pay,String remark){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("income", null, "uid=? and money=? and time=? and category=? and pay=? and remark=?", new String[]{uid,money,time,category,pay,remark}, null,null,null);			
			List<Income> list=new ArrayList<Income>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Money=cursor.getString(2);
				String Time=cursor.getString(3);
				String Category=cursor.getString(4);
				String Pay=cursor.getString(5);
				String Remark=cursor.getString(6);
				list.add(new Income(id,Uid,Money,Time,Category,Pay,Remark));	
			}			
			cursor.close();
			db.close();
			return list;
		}
		// ���� Uid ��ѯ����
		public List<Income> findincomeUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("income", null, "uid=?", new String[]{uid}, null,null,null);			
			List<Income> list=new ArrayList<Income>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Money=cursor.getString(2);
				String Time=cursor.getString(3);
				String Category=cursor.getString(4);
				String Place=cursor.getString(5);
				String Remark=cursor.getString(6);
				list.add(new Income(id,Uid,Money,Time,Category,Place,Remark));	
			}			
			cursor.close();
			db.close();
			return list;
		}
		
		
		
		
		
		
		
		
			
		// ���֧��
		public void addoutlay(String uid,String money,String time,String category,String place,String remark){
			SQLiteDatabase db=helper.getWritableDatabase();
			db.execSQL("insert into outlay(uid,money,time,category,place,remark) values(?,?,?,?,?,?)",new Object[]{uid,money,time,category,place,remark});
			db.close();
		}
		// ��ѯ֧��
		public List<Outlay> findoutlay(String uid,String money,String time,String category,String place,String remark){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("outlay", null, "uid=? and money=? and time=? and category=? and place=? and remark=?", new String[]{uid,money,time,category,place,remark}, null,null,null);
			
			List<Outlay> list=new ArrayList<Outlay>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Money=cursor.getString(2);
				String Time=cursor.getString(3);
				String Category=cursor.getString(4);
				String Place=cursor.getString(5);
				String Remark=cursor.getString(6);
				list.add(new Outlay(id,Uid,Money,Time,Category,Place,Remark));	
			}			
			cursor.close();
			db.close();
			return list;
		}
		// ���� Uid ��ѯ֧��
		public List<Outlay> findoutlayUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("outlay", null, "uid=?", new String[]{uid}, null,null,null);			
			List<Outlay> list=new ArrayList<Outlay>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Money=cursor.getString(2);
				String Time=cursor.getString(3);
				String Category=cursor.getString(4);
				String Place=cursor.getString(5);
				String Remark=cursor.getString(6);
				list.add(new Outlay(id,Uid,Money,Time,Category,Place,Remark));	
			}			
			cursor.close();
			db.close();
			return list;
		}
		// ɾ��֧��
		public long deleteoutlay(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("outlay","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// �޸�֧��
		public int updateoutlay(String id,String money,String time,String category,String place,String remark){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("id", id);
			values.put("money", money);
			values.put("time", time);
			values.put("category", category);
			values.put("place", place);
			values.put("remark", remark);
			int flag=db.update("income", values, "id=?", new String[]{id});
			db.close();
			return flag;
		}
		
		
		
		
		
		// ��ӱ�ǩ
		public long addnote(String uid,String info){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();	
			values.put("uid",uid);
			values.put("info", info);
			long flag=db.insert("note",null,values);
			db.close();
			return flag;
		}
		// ɾ����ǩ
		public long deletenote(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("note","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// �޸ı�ǩ
		public int updatenote(String id,String info){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("id", id);
			values.put("info", info);
			int flag=db.update("note", values, "id=?", new String[]{id});
			db.close();
			return flag;
		}
		// ����  uid ��ѯ��ǩ
		public List<Note> findnoteUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("note", null, "uid=?", new String[]{uid}, null,null,null);
			List<Note> list=new ArrayList<Note>();
			while(cursor.moveToNext()){
				//���Ը���������ȡ����
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));				
				String info=cursor.getString(2);
				list.add(new Note(id,Uid,info));	
			}
			cursor.close();
			db.close();
			return list;
		}
		
		// ��ѯ��ǩ
		public boolean findnote(String id){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where id=?",new String[]{id});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
		// ͨ�� uid ��  info  ��ѯ��ǩ      �ҵ�����   true ���򷵻�  false
		public boolean findnote(String uid,String info){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where uid=? and info=?",new String[]{uid,info});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
		// ͨ�� id ��  info  ��ѯ��ǩ      �ҵ�����   true ���򷵻�  false
		public boolean findnote1(String id,String info){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where id=? and info=?",new String[]{id,info});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
}
