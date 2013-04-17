package com.drooddesign.mybrary;

import com.drooddesign.mybrary.database.BookUtil;
import com.drooddesign.mybrary.database.MybraryContentProvider;
import com.drooddesign.mybrary.database.MybraryProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditBook extends Activity {
	
	private EditText mTitle;
	private EditText mAuthor;
	private Button mSave;
	private Button mDelete;
	private Button mCancel;
	private Bundle mbundle;
	private Boolean mIdInit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_book);
		
		mTitle = (EditText) findViewById(R.id.titleedit);
		mAuthor = (EditText) findViewById(R.id.authoredit);
		
		mSave = (Button) findViewById(R.id.save);
		mSave.setOnClickListener(mSaveListener);
		
		mDelete = (Button) findViewById(R.id.delete);
		mDelete.setOnClickListener(mDeleteListener);
		
		mCancel = (Button) findViewById(R.id.cancel);
		mCancel.setOnClickListener(mCancelListener);
		
		mbundle = getIntent().getExtras();
		if(mbundle != null)
		{
			mIdInit = true;
			initWithId();
		} else {
			mIdInit = false;
			//init();
		}
		//setOnClickListener(R.id.save, mSave);
	}
	
	private void initWithId() {
		String mBookId = mbundle.getString("id");
		Cursor mBook = BookUtil.getBookById(this, mBookId);
		
		if(mBook != null) {
			if(mBook.moveToFirst()) {
				mTitle.setText(mBook.getString(mBook.getColumnIndex(MybraryProvider.BookTable.mColTitle)));
				mAuthor.setText(mBook.getString(mBook.getColumnIndex(MybraryProvider.BookTable.mColAuthor)));
			}
		}
		
	}
	
	private void init() {
		mTitle.setText("");
		mAuthor.setText("");
	}
	

	private OnClickListener mSaveListener = new OnClickListener() {
		@Override
		public void onClick(View v){
			if(!mIdInit){
				
				Uri mNewUri;
				ContentValues mNewValues = new ContentValues();
				
				mNewValues.put(MybraryProvider.BookTable.mColTitle, mTitle.getText().toString());
				mNewValues.put(MybraryProvider.BookTable.mColAuthor, mAuthor.getText().toString());
				
				mNewUri = getContentResolver().insert(MybraryContentProvider.CONTENT_URI, mNewValues);

			} else {
				Integer mUpdated;
				ContentValues mNewValues = new ContentValues();
				
				mNewValues.put(MybraryProvider.BookTable.mColTitle, mTitle.getText().toString());
				mNewValues.put(MybraryProvider.BookTable.mColAuthor, mAuthor.getText().toString());
				
				String mWhereClause = "( " + MybraryProvider.BookTable.mId + " = ? ) ";
				String mBookId = mbundle.getString("id");
				
				String[] mWhereArgs = { mBookId };
				
				
				mUpdated = getContentResolver().update(MybraryContentProvider.CONTENT_URI, mNewValues, mWhereClause, mWhereArgs);
				
			}
			
			Intent i = new Intent(EditBook.this, BookListView.class);
			startActivity(i);
		}
	};
	
	private OnClickListener mDeleteListener = new OnClickListener() {
		@Override
		public void onClick(View v){
			//TODO DELETE
			
			Intent i = new Intent(EditBook.this, BookListView.class);
			startActivity(i);
		}
	};
	
	private OnClickListener mCancelListener = new OnClickListener() {
		@Override
		public void onClick(View v){
			Intent i = new Intent(EditBook.this, BookListView.class);
			startActivity(i);
		}
	};
	
	
	
	private void setOnClickListener(int id, OnClickListener l) {
		View v = this.findViewById(id);
		v.setOnClickListener(l);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_book, menu);
		return true;
	}

}
