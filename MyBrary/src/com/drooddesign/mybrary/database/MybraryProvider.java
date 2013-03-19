package com.drooddesign.mybrary.database;

import android.provider.BaseColumns;

public class MybraryProvider {
	public static final String mAUTHORITY = "com.drooddesign.mybrary.database.mybrarycontentprovider";
	private static final String  mScheme = "content://";
	
	public MybraryProvider(){
	}
	
	public class BookTable implements BaseColumns {
		public static final String mTableName = "books";
		
		//public static final String path
		//public static final String ID 
		
		public static final String mId = "_id";
		public static final String mColTitle = "title";
		public static final String mColAuthor = "author";
		public static final String mColISBN = "isbn";
		
		
		
	}
}
