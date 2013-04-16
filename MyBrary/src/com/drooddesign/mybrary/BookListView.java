package com.drooddesign.mybrary;

import com.drooddesign.mybrary.database.MybraryContentProvider;
import com.drooddesign.mybrary.database.MybraryProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

public class BookListView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list_view);
		
		Spinner spinner = (Spinner) findViewById(R.id.category);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
				R.array.test, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		setOnClickListener(R.id.add_book, mAddBook);
		
		final ListView bookList = (ListView) findViewById(R.id.book_list);
		
		String[] projection = { MybraryProvider.BookTable.mId, MybraryProvider.BookTable.mColTitle };
		String[] uiBindFrom = { MybraryProvider.BookTable.mColTitle };
		
		int[] uiBindTo = {R.id.title };
		//TEST TES TEST
		Cursor tutorials = this.managedQuery( MybraryContentProvider.CONTENT_URI, projection, null, null, null);
		CursorAdapter mCursorAdapter = new SimpleCursorAdapter(this.getApplicationContext(), R.layout.list_item, tutorials,
				uiBindFrom, uiBindTo);
		bookList.setAdapter(mCursorAdapter);
		bookList.setOnItemClickListener(new ListView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i = new Intent(BookListView.this, BookDetails.class);
				
				startActivity(i);
				
			}			
		});
		
	}
	
	/*public void onListItemClick(ListView l, View v, int position, long id) {
		/*String projection[] = { MybraryProvider.BookTable.mId, MybraryProvider.BookTable.mColTitle, 
				MybraryProvider.BookTable.mColAuthor };
		Cursor tutorialCursor = this.getContentResolver().query(Uri.withAppendedPath( MybraryContentProvider.CONTENT_URI,
				String.valueOf(id)), projection, null, null, null);
		
		Intent i = new Intent(BookListView.this, BookDetails.class);
		
		startActivity(i);
	}*/
	
	private void setOnClickListener(int id, OnClickListener l) {
		View v = this.findViewById(id);
		v.setOnClickListener(l);
		//v.setBackgroundResource(resid);
	}
	
	private OnClickListener mAddBook = new OnClickListener() {
		@Override
		public void onClick(View v){
			//TODO
		}
	};

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list_view, menu);
		
		return true;
	}

}
