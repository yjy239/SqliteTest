package com.example.practiseforsqlite;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button button;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private MyDataBaseHelper dbHelper;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.createSharedPreference);
        button1 = (Button)findViewById(R.id.createSqlite);
        button2 = (Button)findViewById(R.id.addlist);
        button3 = (Button)findViewById(R.id.deletetable);
        button4 = (Button)findViewById(R.id.readSharePreference);
        button5 = (Button)findViewById(R.id.readSqlite);
        button6 = (Button)findViewById(R.id.updatesqlite);
        dbHelper = new MyDataBaseHelper(this, "BookStore", null, 1);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.createSharedPreference:
			SharedPreferences.Editor editor = getSharedPreferences("yjydata", MODE_PRIVATE).edit();
			editor.putString("name", "yjy");
			editor.putInt("age", 22);
			editor.putBoolean("married", false);
			editor.commit();
			Toast.makeText(this, "create a xml", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.readSharePreference:
			SharedPreferences pref = getSharedPreferences("yjydata", MODE_PRIVATE);
			String name = pref.getString("name", "1");
			int age = pref.getInt("age", 1);
			boolean married = pref.getBoolean("married is", false);
			Log.e("name", name);
			Log.e("age", ""+age);
			Log.e("married", ""+married);
			break;
			
		case R.id.createSqlite:
			dbHelper.getWritableDatabase();
			break;
			
		case R.id.addlist:
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "Book1");
			values.put("author", "a");
			values.put("price", 16.64);
			db.insert("Book", null, values);
			values.clear();
			break;
			
		case R.id.updatesqlite:
			SQLiteDatabase db1 = dbHelper.getWritableDatabase();
			ContentValues values1 = new ContentValues();
			values1.put("author", "b");
			db1.update("Book", values1, "name = ?", new String[]{"Book1"});
			break;
			
		case R.id.readSqlite:
			SQLiteDatabase db2 = dbHelper.getWritableDatabase();
			Cursor cursor = db2.query("Book", null, null, null, null, null, null);
			if(cursor.moveToFirst()){
				do{
					String dbname = cursor.getString(cursor.getColumnIndex("name"));
					String author = cursor.getString(cursor.getColumnIndex("author"));
					double price = cursor.getDouble(cursor.getColumnIndex("price"));
					Log.e("query", ""+dbname);
					Log.e("query", ""+author);
					Log.e("query", ""+price);
				}while(cursor.moveToNext());
			}
			cursor.close();
			break;
			
		case R.id.deletetable:
			SQLiteDatabase db3 = dbHelper.getWritableDatabase();
			db3.delete("Book", "price > ?", new String[]{"10"});
			break;

		default:
			break;
		}
	}
    
    
    
}
