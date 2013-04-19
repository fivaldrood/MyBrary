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
import android.widget.TextView;

public class BookDetails extends Activity {
	
	private String mBookId;
	
	private TextView mTitle;
	private TextView mAuthor;
	private Button mEdit;
	private Button mCancel;
	private Bundle mBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details);
		
		mEdit = (Button) findViewById(R.id.editbutton);
		mCancel = (Button) findViewById(R.id.cancelbutton);
		
		mTitle = (TextView) findViewById(R.id.title);
		mAuthor = (TextView) findViewById(R.id.author);
		
		mBundle = getIntent().getExtras();
		if(mBundle != null)
		{			
			 init();	 
		}
		
		mEdit.setOnClickListener(mEditListener);
		mCancel.setOnClickListener(mCancelListener);
	}
	
	private void init() {
		mBookId = mBundle.getString("id");
		Cursor mBook = BookUtil.getBookById(this, mBookId);
		
		if(mBook != null) {
			if(mBook.moveToFirst()) {
				mBookId = mBook.getString(mBook.getColumnIndex(MybraryProvider.BookTable.mId));
				mTitle.setText(mBook.getString(mBook.getColumnIndex(MybraryProvider.BookTable.mColTitle)));
				mAuthor.setText("by " + mBook.getString(mBook.getColumnIndex(MybraryProvider.BookTable.mColAuthor)));
			}
		}	
	}

	private OnClickListener mEditListener = new OnClickListener() {
		@Override
		public void onClick(View v){
			Intent i = new Intent(BookDetails.this, EditBook.class);
			
			Bundle bundle = new Bundle();
			bundle.putString("id", mBookId);
			
			i.putExtras(bundle);
			
			startActivity(i);
		}
	};
	
	private OnClickListener mCancelListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(BookDetails.this, BookListView.class);
			
			startActivity(i);
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_details, menu);
		return true;
	}

}
