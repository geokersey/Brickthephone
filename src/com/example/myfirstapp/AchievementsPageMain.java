package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AchievementsPageMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievements_page_main);
		
		ProgressBar medalsProgressBar = (ProgressBar)findViewById(R.id.goalProgressBar);
		
		MedalsDatabase database = new MedalsDatabase(getApplicationContext());

		database.markPriority();
		TextView nextMedalText = (TextView) findViewById(R.id.NextMedalText);
		nextMedalText.setText(database.getNextPriority());
		
		medalsProgressBar.setMax( database.getNumberOfMedals());
		medalsProgressBar.setProgress( database.getNumberOfDoneMedals());
		
		TextView latestMedalText = (TextView) findViewById(R.id.LatestMedalText);
		latestMedalText.setText(database.getMostRecentAchieved());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.achievements_page_main, menu);
		return true;
	}
	
	public void goToListOfMedals(View view) {
    	
    	//Create intent to go to Achievements screen
    	Intent intent = new Intent(this, AchievementsListPage.class);
    	
    	startActivity(intent);
	}
	
	public void goToSuggest(View view){

    	//Create intent to go to Achievements screen
    	Intent intent = new Intent(this, CreateMedal.class);
    	
    	startActivity(intent);
	}
}
