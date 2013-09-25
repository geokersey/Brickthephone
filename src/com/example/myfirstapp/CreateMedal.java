package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CreateMedal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_medal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_medal, menu);
		return true;
	}

	public void submit(View view){
		MedalsDatabase database = new MedalsDatabase(getApplicationContext());
		EditText descEditText = (EditText) findViewById(R.id.editDescText);
		String desc = descEditText.toString();
		descEditText.setText("");
		
		EditText titleEditText = (EditText) findViewById(R.id.editTitleText);
		String title = titleEditText.toString();
		titleEditText.setText("");
		
		database.addAchievement(title, desc);
	}
}
