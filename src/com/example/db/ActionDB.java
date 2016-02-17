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
 * 数据库操作模块
 * @author Administrator
 *
 */
public class ActionDB {
		private LicaiSQLiteOpenHelper helper;
		public ActionDB(Context context){
			helper=new LicaiSQLiteOpenHelper(context);
		}
		// 添加用户
		public long adduser(String name,String pass){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("name", name);
			values.put("pass", pass);
			long flag=db.insert("user",null,values);
			db.close();
			return flag;
		}
		// 删除用户
		public int deleteuser(String uid){
			SQLiteDatabase db=helper.getWritableDatabase();	
			int flag=db.delete("user","uid=?",new String[]{uid});
			db.close();
			return flag;
		}
		// 修改用户
		public int updateuser(String uid,String name,String pass){
			// 获取一个可读写的 SQLiteDatabase
			SQLiteDatabase db=helper.getWritableDatabase();
			 //创建一个 ContentValueszc   对象
			ContentValues values=new ContentValues();
			//将参数以  Key，value 的形式添加进去
			values.put("name",name);
			values.put("pass",pass);
			// 执行修改方法
			int flag=db.update("user", values, "uid=?", new String[]{uid});
			// 关闭数据库
			db.close();
			return flag;
		}
		// 修改用户密码
		public void updateuser(String uid,String pass){
			// 获取一个可读写的 SQLiteDatabase
			SQLiteDatabase db=helper.getWritableDatabase();
			 //创建一个 ContentValueszc   对象
			ContentValues values=new ContentValues();
			//将参数以  Key，value 的形式添加进去			
			values.put("pass",pass);
			// 执行修改方法			
			db.update("user", values, "uid=?", new String[]{uid});			
			//db.execSQL("update user set pass=? where uid=?", new Object[]{pass, uid});
			
			// 关闭数据库
			db.close();
			//return flag;
		}
		// 查询用户
		public List<User> finduser(String name,String pass){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "name=? and pass=?", new String[]{name,pass}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}		
		// 查询用户名是否存在
		public List<User> finduser(String name){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "name=?", new String[]{name}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}
		// 通过 uid 查询用户信息
		public List<User> finduserUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("user", null, "uid=?", new String[]{uid}, null,null,null);
			List<User> list=new ArrayList<User>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));
				String Name=cursor.getString(1);
				String Pass=cursor.getString(2);
				list.add(new User(Uid,Name,Pass));	
			}
			cursor.close();
			db.close();
			return list;
		}
				
		
		
		
		
		
		// 添加收入
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
		// 删除收入
		public long deleteincome(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("income","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// 修改收入
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
		// 查询收入
		public boolean findincome(String id){
			// 获取可读的数据库
			SQLiteDatabase db=helper.getReadableDatabase();
			// 查询数据库操作 ： 表名、查询列名、查询条件、查询参数值、分组条件、having条件、排序方式
			Cursor cursor=db.query("income", null, "id=?", new String[]{id}, null,null,null);
			// 是否有下一条值
			boolean result=cursor.moveToNext();
			// 关闭游标
			cursor.close();
			// 关闭数据库
			db.close();
			return result;
		}
		// 查询收入
		public List<Income> findincome(String uid,String money,String time,String category,String pay,String remark){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("income", null, "uid=? and money=? and time=? and category=? and pay=? and remark=?", new String[]{uid,money,time,category,pay,remark}, null,null,null);			
			List<Income> list=new ArrayList<Income>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
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
		// 根据 Uid 查询收入
		public List<Income> findincomeUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("income", null, "uid=?", new String[]{uid}, null,null,null);			
			List<Income> list=new ArrayList<Income>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
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
		
		
		
		
		
		
		
		
			
		// 添加支出
		public void addoutlay(String uid,String money,String time,String category,String place,String remark){
			SQLiteDatabase db=helper.getWritableDatabase();
			db.execSQL("insert into outlay(uid,money,time,category,place,remark) values(?,?,?,?,?,?)",new Object[]{uid,money,time,category,place,remark});
			db.close();
		}
		// 查询支出
		public List<Outlay> findoutlay(String uid,String money,String time,String category,String place,String remark){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("outlay", null, "uid=? and money=? and time=? and category=? and place=? and remark=?", new String[]{uid,money,time,category,place,remark}, null,null,null);
			
			List<Outlay> list=new ArrayList<Outlay>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
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
		// 根据 Uid 查询支出
		public List<Outlay> findoutlayUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("outlay", null, "uid=?", new String[]{uid}, null,null,null);			
			List<Outlay> list=new ArrayList<Outlay>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
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
		// 删除支出
		public long deleteoutlay(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("outlay","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// 修改支出
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
		
		
		
		
		
		// 添加便签
		public long addnote(String uid,String info){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();	
			values.put("uid",uid);
			values.put("info", info);
			long flag=db.insert("note",null,values);
			db.close();
			return flag;
		}
		// 删除便签
		public long deletenote(String id){
			SQLiteDatabase db=helper.getWritableDatabase();			
			long flag=db.delete("note","id=?",new String[]{id});
			db.close();
			return flag;
		}
		// 修改便签
		public int updatenote(String id,String info){
			SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("id", id);
			values.put("info", info);
			int flag=db.update("note", values, "id=?", new String[]{id});
			db.close();
			return flag;
		}
		// 根据  uid 查询便签
		public List<Note> findnoteUid(String uid){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.query("note", null, "uid=?", new String[]{uid}, null,null,null);
			List<Note> list=new ArrayList<Note>();
			while(cursor.moveToNext()){
				//可以根据列名获取索引
				int id=cursor.getInt(cursor.getColumnIndex("id"));
				int Uid=cursor.getInt(cursor.getColumnIndex("uid"));				
				String info=cursor.getString(2);
				list.add(new Note(id,Uid,info));	
			}
			cursor.close();
			db.close();
			return list;
		}
		
		// 查询便签
		public boolean findnote(String id){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where id=?",new String[]{id});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
		// 通过 uid 和  info  查询便签      找到返回   true 否则返回  false
		public boolean findnote(String uid,String info){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where uid=? and info=?",new String[]{uid,info});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
		// 通过 id 和  info  查询便签      找到返回   true 否则返回  false
		public boolean findnote1(String id,String info){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select *from note where id=? and info=?",new String[]{id,info});
			boolean result=cursor.moveToNext();
			cursor.close();
			db.close();
			return result;
		}
}
