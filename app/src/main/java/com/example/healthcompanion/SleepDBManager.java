package com.example.healthcompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class SleepDBManager {


    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;


    public SleepDBManager(Context c) {
        context = c;
    }

    public SleepDBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public void insert(String date, String analysis,String sleepDuration) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CREATION_DATE, date);
        contentValue.put(DatabaseHelper.ANALYSIS, analysis);
        contentValue.put(DatabaseHelper.SLEEP_DURATION, sleepDuration);
        database.insert(DatabaseHelper.TABLE_STEPS_SUMMARY, null, contentValue);
    }

    public Cursor readExerciseData() {
        String[] columns = new String[]{DatabaseHelper.ID, DatabaseHelper.CREATION_DATE, DatabaseHelper.ANALYSIS, DatabaseHelper.SLEEP_DURATION};
        Cursor cursor = database.query(DatabaseHelper.TABLE_STEPS_SUMMARY, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String newDate, String newAnalysis, String newSleepDuration) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CREATION_DATE, newDate);
        cv.put(DatabaseHelper.START, newAnalysis);
        cv.put(DatabaseHelper.STOP, newSleepDuration);
        int i = database.update(DatabaseHelper.TABLE_STEPS_SUMMARY, cv, DatabaseHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_STEPS_SUMMARY, DatabaseHelper.ID + "=" + id, null);
    }

}
