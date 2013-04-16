package com.drooddesign.mybrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MybraryDatabase extends SQLiteOpenHelper {
	//private static final String TAG = "MybraryDatabaseHelper";
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "MybraryData.db";
	
	public MybraryDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		BookTable.onCreate(db);
		seedData(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		BookTable.onUpgrade(db, oldVersion, newVersion);
	}
	
	//Seed some starting data
	private void seedData(SQLiteDatabase db) {
		db.execSQL("insert into book (title, author) values ('Book 1', 'Author 1');");
		db.execSQL("insert into book (title, author) values ('Book 2', 'Author 2');");
		db.execSQL("insert into book (title, author) values ('Book 3', 'Author 3');");
		db.execSQL("insert into book (title, author) values ('Book 4', 'Author 4');");
		db.execSQL("insert into book (title, author) values ('Book 5', 'Author 5');");
	}
}
