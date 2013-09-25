package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void goToAchievements (View view) {
    	
    	//Create intent to go to Achievements screen
    	Intent intent = new Intent(this, AchievementsPageMain.class);
    	
    	startActivity(intent);
    }
    
    public void goToCamera(View view) {
    	Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    	startActivity(intent);
    }
    
    public void goToTips(View view){
    	Intent intent = new Intent(this, TipsPage.class);
    	startActivity(intent);
    }
}
