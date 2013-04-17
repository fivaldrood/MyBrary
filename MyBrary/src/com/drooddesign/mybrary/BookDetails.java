package com.drooddesign.mybrary;

import com.drooddesign.mybrary.database.MybraryContentProvider;
import com.drooddesign.mybrary.database.MybraryProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BookDetails extends Activity {
	
	private String mBookId;
	
	private TextView mId;
	private Button mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details);
		
		mId = (TextView) findViewById(R.id.id);
		mEdit = (Button) findViewById(R.id.editbutton);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null)
		{
			 mBookId = bundle.getString("id");
		}
		
		mId.setText(mBookId);
		mEdit.setOnClickListener(mEditListener);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_details, menu);
		return true;
	}

}
