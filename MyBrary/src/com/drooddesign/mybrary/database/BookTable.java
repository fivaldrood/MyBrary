package com.drooddesign.mybrary.database;

import android.database.sqlite.SQLiteDatabase;

public class BookTable {
	//private static final String mTableName = MybraryProvider.BookTable.mTableName;
	
	private static final String CREATE_BOOK_TABLE = "create table " + MybraryProvider.BookTable.mTableName + " ("
			+ MybraryProvider.BookTable.mId + " integer primary key autoincrement, "
			+ MybraryProvider.BookTable.mColTitle + " text not null, "
			+ MybraryProvider.BookTable.mColAuthor + " text not null, "
			+ MybraryProvider.BookTable.mColGenre + " text not null, "
			+ MybraryProvider.BookTable.mColLent + "integer, "
			+ MybraryProvider.BookTable.mColLender + " text, "
			+ MybraryProvider.BookTable.mColLendDate + "text"
			+ ");";
	
	public static void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_BOOK_TABLE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + MybraryProvider.BookTable.mTableName);
		onCreate(db);
	}
}
