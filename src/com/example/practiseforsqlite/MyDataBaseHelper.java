package com.example.practiseforsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

public class MyDataBaseHelper extends SQLiteOpenHelper{
	private Context context;
	
	private static final String CREATE_BOOK = " create table Book ("
			+"id integer primary key autoincrement,"
			+"author text,"
			+"price real,"
			+"name text)";
	
	public MyDataBaseHelper(Context context,String name,CursorFactory factory,
			int version){
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_BOOK);
		Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
