package com.drooddesign.mybrary.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.drooddesign.mybrary.database.MybraryContentProvider;

public class BookUtil {
	public static final String[] projectionAll = { MybraryProvider.BookTable.mId, MybraryProvider.BookTable.mColTitle, MybraryProvider.BookTable.mColAuthor };
	public static Cursor getBookById(Context context, String id){
		Uri mUriQuery = Uri.parse(MybraryContentProvider.CONTENT_URI + "/" + id);
		Cursor mQuery = context.getContentResolver().query( mUriQuery, projectionAll, null, null, null );
		return mQuery;
	}
}
