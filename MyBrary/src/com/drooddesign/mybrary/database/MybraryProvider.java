package com.drooddesign.mybrary.database;

import android.provider.BaseColumns;

public class MybraryProvider {
	public static final String mAUTHORITY = "com.drooddesign.mybrary.database.mybrarycontentprovider";
	private static final String  mScheme = "content://";
	
	public MybraryProvider(){
	}
	
	public class BookTable implements BaseColumns {
		public static final String mTableName = "books";
		
		public static final String mBasePath = "/books";
		public static final String mPathId = "/books/"; 
		
		//integer primary key autoincrement
		public static final String mId = "_id";
		//text not null
		public static final String mColTitle = "title";
		//text not null
		public static final String mColAuthor = "author";
		//text not null
		public static final String mColGenre = "genre";
		//integer 0 = false, 1 = true
		public static final String mColLent = "lent";
		//text
		public static final String mColLender = "lender";
		//text
		public static final String mColLendDate = "lenddate";
		
		//public static final String mColISBN = "isbn";
		
		public BookTable(){
		}
	}
}
