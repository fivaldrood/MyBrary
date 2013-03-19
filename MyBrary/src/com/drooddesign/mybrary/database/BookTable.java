package com.drooddesign.mybrary.database;

public class BookTable {
	
	
	private static final String CREATE_TABLE = "create table " + MybraryProvider.BookTable.mTableName + " ("
			+ MybraryProvider.BookTable.mId + " integer primary key autoincrement, "
			+ MybraryProvider.BookTable.mColTitle + " text not null, "
			+ MybraryProvider.BookTable.mColAuthor + " text not null, "
			+ MybraryProvider.BookTable.mColISBN + " text not null);";
}
