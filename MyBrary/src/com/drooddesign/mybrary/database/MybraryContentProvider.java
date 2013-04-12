package com.drooddesign.mybrary.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MybraryContentProvider extends ContentProvider {
	
	private MybraryDatabase mDB;
	
	public static final int mBooks = 1;
	public static final int mBooksId = 2;
	
	private static final String BOOKS_BASE_PATH = MybraryProvider.BookTable.mTableName;
	public static final Uri CONTENT_URI = Uri.parse(MybraryProvider.mScheme + MybraryProvider.mAUTHORITY + "/" + BOOKS_BASE_PATH);
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/mt-book";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mt-book";
	
	
	private static final UriMatcher mURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		mURIMatcher.addURI(MybraryProvider.mAUTHORITY, BOOKS_BASE_PATH, mBooks);
		mURIMatcher.addURI(MybraryProvider.mAUTHORITY, BOOKS_BASE_PATH + "/#", mBooksId);
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = mURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mDB.getWritableDatabase();
		int rowsAffected = 0;
		switch (uriType) {
		case mBooks:
			rowsAffected = sqlDB.delete(MybraryProvider.BookTable.mTableName,
					selection, selectionArgs);
			break;
		case mBooksId:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = sqlDB.delete(MybraryProvider.BookTable.mTableName, 
						MybraryProvider.BookTable.mId + "=" + id, null);
			} else {
				rowsAffected = sqlDB.delete(MybraryProvider.BookTable.mTableName,
						selection + " and " + MybraryProvider.BookTable.mId + "=" + id,
						selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsAffected;
	}

	@Override
	public String getType(Uri uri) {
		int uriType = mURIMatcher.match(uri);
		switch (uriType) {
		case mBooks:
			return CONTENT_TYPE;
		case mBooksId:
			return CONTENT_ITEM_TYPE;
		default:
			return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = mURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mDB.getWritableDatabase();
		
		switch (uriType) {
		case mBooks:
			try {
				long newID = sqlDB.insertOrThrow(MybraryProvider.BookTable.mTableName,
						null, values);
				if (newID > 0) {
					Uri newUri = ContentUris.withAppendedId(uri, newID);
					getContext().getContentResolver().notifyChange(uri, null);
					return newUri;
				} else {
					throw new SQLException("Failed to insert row int " + uri);
				}
				
			} catch (SQLiteConstraintException e) {
				Log.i("CONTENT_PROVIDER_TAG", "Ignoring constraint failure.");
			}
		default:
			throw new IllegalArgumentException("Invalid URI for insert");
		}
		
		//return null;
	}

	@Override
	public boolean onCreate() {
		mDB = new MybraryDatabase(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(MybraryProvider.BookTable.mTableName);
		int uriType = mURIMatcher.match(uri);
		switch (uriType) {
		case mBooksId:
			queryBuilder.appendWhere(MybraryProvider.BookTable.mId + "=" + uri.getLastPathSegment());
			break;
		case mBooks:
			
			break;		
		default:
			throw new IllegalArgumentException("Unknown URI");
		}
		Cursor cursor = queryBuilder.query(mDB.getReadableDatabase(),
				projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = mURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mDB.getWritableDatabase();
		
		int rowsAffected;
		
		switch (uriType) {
		case mBooksId:
			String id = uri.getLastPathSegment();
			StringBuilder modSelection = new StringBuilder(MybraryProvider.BookTable.mId + "=" + id);
			
			if(!TextUtils.isEmpty(selection)) {
				modSelection.append(" AND " + selection);
			}
			
			rowsAffected = sqlDB.update(MybraryProvider.BookTable.mTableName,
					values, modSelection.toString(), null);
		break;
		case mBooks:
			rowsAffected = sqlDB.update(MybraryProvider.BookTable.mTableName,
					values, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI");
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsAffected;
	}

}
