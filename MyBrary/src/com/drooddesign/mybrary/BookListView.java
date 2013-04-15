package com.drooddesign.mybrary;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
	}
	
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
