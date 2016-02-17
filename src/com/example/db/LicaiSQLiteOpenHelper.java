package com.example.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * 数据库创建模块
 * @author Administrator
 *
 */

public class LicaiSQLiteOpenHelper extends SQLiteOpenHelper{	
	public LicaiSQLiteOpenHelper(Context context) {
		super(context, "Licia.db", null, 5);		
	}
	@Override
	// 数据库第一次被创建时调用
	public void onCreate(SQLiteDatabase db) {		
		db.execSQL("create table user(uid integer primary key autoincrement,name varchar(20),pass varchar(20))");
		db.execSQL("create table income(id integer primary key autoincrement,uid int not null,money varchar(20),time varchar(30),category varchar(255),pay varchar(100),remark varchar(255))");
		db.execSQL("create table outlay(id integer primary key autoincrement,uid int not null,money varchar(20),time varchar(30),category varchar(255),place varchar(255),remark varchar(255))");
		db.execSQL("create table note(id integer primary key autoincrement,uid int not null,info varchar(255))");		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("更新！");
	}
}