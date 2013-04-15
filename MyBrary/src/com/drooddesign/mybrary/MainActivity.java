package com.drooddesign.mybrary;

import com.drooddesign.mybrary.database.MybraryContentProvider;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		//MybraryContentProvider test = new MybraryContentProvider();
		
		setOnClickListener(R.id.main_menu_books, mBooksHandler);
		setOnClickListener(R.id.main_menu_cds, mCdHandler);
		
		
	}
	
	private void setOnClickListener(int id, OnClickListener l) {
		View v = this.findViewById(id);
		v.setOnClickListener(l);
		//v.setBackgroundResource(resid);
	}
	
	private OnClickListener mBooksHandler = new OnClickListener() {
		@Override
		public void onClick(View v){
			// TODO TODO
			Intent i = new Intent(MainActivity.this, BookListView.class);
			startActivity(i);
		}
	};
	
	private OnClickListener mCdHandler = new OnClickListener() {
		@Override
		public void onClick(View v){
			//TODO
		}
	};

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
	//	return true;
	//}

}
