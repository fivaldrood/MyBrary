package com.drooddesign.mybrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MybraryDatabase extends SQLiteOpenHelper {
	private static final String TAG = "MybraryDatabaseHelper";
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "MybraryData";
	
	public MybraryDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		BookTable.onCreate(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		BookTable.onUpgrade(db, oldVersion, newVersion);
	}
}
