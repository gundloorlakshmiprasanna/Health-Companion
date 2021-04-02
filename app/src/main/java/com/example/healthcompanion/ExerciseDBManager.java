package com.example.healthcompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ExerciseDBManager {


    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public ExerciseDBManager(Context c) {
        context = c;
    }

    public ExerciseDBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String date, String start, String stop) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CREATION_DATE,date);
        contentValue.put(DatabaseHelper.START, start);
        contentValue.put(DatabaseHelper.STOP, stop);
        database.insert(DatabaseHelper.TABLE_EXERCISE_SUMMARY, null, contentValue);
    }

    public Cursor readExerciseData() {
        String[] columns = new String[] { DatabaseHelper.ID, DatabaseHelper.CREATION_DATE, DatabaseHelper.START, DatabaseHelper.STOP };
        Cursor cursor = database.query(DatabaseHelper.TABLE_EXERCISE_SUMMARY, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String newDate, String newStart, String newStop) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CREATION_DATE, newDate);
        cv.put(DatabaseHelper.START, newStart);
        cv.put(DatabaseHelper.STOP, newStop);
        int i = database.update(DatabaseHelper.TABLE_EXERCISE_SUMMARY, cv, DatabaseHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_EXERCISE_SUMMARY  , DatabaseHelper.ID + "=" + id, null);
    }

}