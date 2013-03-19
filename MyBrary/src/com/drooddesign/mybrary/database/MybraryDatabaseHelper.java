package com.drooddesign.mybrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MybraryDatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "MybraryDatabaseHelper";
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "MybraryData";
	
	public MybraryDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
