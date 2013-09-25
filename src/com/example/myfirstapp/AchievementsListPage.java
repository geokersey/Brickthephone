package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.database.sqlite.SQLiteCursor;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;

public class AchievementsListPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievements_list_page);
		MedalsDatabase database = new MedalsDatabase(getApplicationContext());

		SQLiteCursor cursor = database.queueAll();
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.medallistitem,(Cursor)cursor,new String[]{"goalName","goalDescription"},new int[]{R.id.medalname,R.id.medeldesc},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		
		ListView list = (ListView)findViewById(R.id.medalsList);
		list.setAdapter(cursorAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.achievements_list_page, menu);
		return true;
	}

}
