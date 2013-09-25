package com.example.myfirstapp;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Date;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;
import android.content.res.AssetManager;

public class MedalsDatabase extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "goals";
	private static int DATABASE_VERSION = 4;
	private Context dbContext;

	private static String DATABASE_CREATION_STRING = "create table if not exists " + DATABASE_NAME + " ( goalName text, goalDescription text, achieved integer default 0, dateAchieved integer default 0, priority integer default 0);";
	private static String DATABASE_UPDATE_STRING_2_to_3 = "alter table " + DATABASE_NAME + " add column dateAchieved integer default 0; ";
	private static String DATABASE_UPDATE_STRING_3_to_4 = "alter table " + DATABASE_NAME + " add column priority integer default 0";
	
	MedalsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbContext = context;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATION_STRING);
		
		try {
			//getAsset gives InputStream, which we read from with InputStreamReader, which we buffer to improve performance
			BufferedReader fileInput = new BufferedReader(new InputStreamReader( dbContext.getAssets().open("medals.dat")));
			
			String inputLine = fileInput.readLine();
			while (inputLine.length() >= 2){
				String medalName = inputLine.substring(0,inputLine.indexOf(';'));
				String medalDesc = inputLine.substring(inputLine.indexOf(';')+2).replace("\n", "");
				
				db.execSQL("insert into " + DATABASE_NAME + " (goalName, goalDescription) values ( '" + medalName + "' , '" + medalDesc + "' );");
				inputLine = fileInput.readLine();
			}
		}
		catch (java.lang.Exception e){
			System.out.println("HELP ME");
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		switch(oldVersion) {
		case 2:
			db.execSQL(DATABASE_UPDATE_STRING_2_to_3);
		case 3:
			db.execSQL(DATABASE_UPDATE_STRING_3_to_4);
		}
	}
	
	public int getNumberOfMedals(){
		SQLiteDatabase database = this.getReadableDatabase();
		SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("select * from " + DATABASE_NAME + ";", new String[]{});
		
		//database.close();
		cursor.moveToNext();
		return cursor.getCount();
	}

	public int getNumberOfDoneMedals(){
		SQLiteDatabase database = this.getReadableDatabase();
		SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("select * from " + DATABASE_NAME + " where achieved=1;", new String[]{});
				
		//database.close();
		cursor.moveToNext();
		return cursor.getCount();
	}
	
	public void addAchievement(String name, String description){
		SQLiteDatabase database = this.getWritableDatabase();
		database.execSQL("insert into " + DATABASE_NAME + " (goalName, goalDescription) values ( '" + name + "' , '" + description + "' );");
		database.close();
	}
	
	public void markAchieved(String name) {
		SQLiteDatabase database = this.getWritableDatabase();
		database.execSQL("update " + DATABASE_NAME + " set achieved = 1, dateAchieved = " + new Date().getTime() + ", prioritized = 0 where goalName = " + name + ";");
		database.close();
	}
	
	public void markPriority(String name) {
		SQLiteDatabase database = this.getWritableDatabase();
		database.execSQL("update " + DATABASE_NAME + " set priority = 1 where goalName = " + name + ";");
		database.close();
	}
	
	public void markPriority() {
		SQLiteDatabase database = this.getWritableDatabase();
		database.execSQL("update " + DATABASE_NAME + " set priority = 1 where rowid=1;");
		database.close();
	}
	public String getNextPriority() {
		SQLiteDatabase database = this.getReadableDatabase();
		SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("select * from " + DATABASE_NAME + " where priority=1;", new String[]{});
		
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
	
	public String getMostRecentAchieved(){
		SQLiteDatabase database = this.getReadableDatabase();
		SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("select * from " + DATABASE_NAME + " where achieved=1 order by dateAchieved;", new String[]{});
		
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
	

	public SQLiteCursor queueAll() {
		SQLiteDatabase database = this.getReadableDatabase();
		String[] columns = new String[]{"rowid _id, goalName","goalDescription"};
		SQLiteCursor cursor = (SQLiteCursor)database.query(DATABASE_NAME, columns,
		  null, null, null, null, null);
		
		return cursor;
	}
}
