package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TipsPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips_page);
		
		postTip();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tips_page, menu);
		return true;
	}
	
	public void giveTip(View view){
		postTip();
	}
	
	private void postTip(){
		tipsDatabase database = new tipsDatabase(getApplicationContext());
		TextView tipText = (TextView) findViewById(R.id.tipText);
		tipText.setText(database.getTip());
	}

}
