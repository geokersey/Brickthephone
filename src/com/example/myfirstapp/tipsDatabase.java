package com.example.myfirstapp;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Date;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;

public class tipsDatabase extends SQLiteOpenHelper {
	private static String DATABASE_NAME = "tips";
	private static int DATABASE_VERSION = 2;
	private Context dbContext;

	private static String DATABASE_CREATION_STRING = "create table if not exists " + DATABASE_NAME + " ( tipContent text);";

	tipsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbContext = context;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATION_STRING);
		
		try {
			//getAsset gives InputStream, which we read from with InputStreamReader, which we buffer to improve performance
			BufferedReader fileInput = new BufferedReader(new InputStreamReader( dbContext.getAssets().open("tips.dat")));
			
			String inputLine = fileInput.readLine();
			while (inputLine.length() >= 2){
				db.execSQL("insert into " + DATABASE_NAME + " (tipContent) values ( '" + inputLine + "' );");
				inputLine = fileInput.readLine();
			}
		}
		catch (java.lang.Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	public String getTip(){
		SQLiteDatabase database = this.getWritableDatabase();
		SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("select tipContent from " + DATABASE_NAME + " order by random()", new String[]{});
		
		//database.close();
		cursor.moveToNext();
		database.close();
		if (cursor.getCount() > 0){
			return cursor.getString(1);
		}
		else {
			return "";
		}
	}
}