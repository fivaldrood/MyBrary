package com.drooddesign.mybrary.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MybraryContentProvider extends ContentProvider {
	
	private MybraryDatabase mDB;
	
	public static final int mBooks = 1;
	public static final int mBooksId = 2;
	
	
	private static final UriMatcher mURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		mURIMatcher.addURI(MybraryProvider.mAUTHORITY, TODOTODO, mBooks);
		mURIMatcher.addURI(MybraryProvider.mAUTHORITY, TODOTODO, mBooksId);
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
