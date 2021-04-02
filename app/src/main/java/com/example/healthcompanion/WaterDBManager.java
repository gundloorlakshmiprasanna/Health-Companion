package com.example.healthcompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;


public class WaterDBManager {


    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public WaterDBManager(Context c) {
        context = c;
    }

    public WaterDBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String date, String glassCount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CREATION_DATE,date);
        contentValue.put(DatabaseHelper.GLASS_COUNT, glassCount);
        database.insert(DatabaseHelper.TABLE_STEPS_SUMMARY, null, contentValue);
    }

    public Cursor readStepsEntries() {
        String[] columns = new String[] { DatabaseHelper.ID, DatabaseHelper.CREATION_DATE, DatabaseHelper.GLASS_COUNT,  };
        Cursor cursor = database.query(DatabaseHelper.TABLE_WATER_INTAKE_SUMMARY, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String newDate, String newGlassCount ) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CREATION_DATE, newDate);
        cv.put(DatabaseHelper.GLASS_COUNT, newGlassCount);

        int i = database.update(DatabaseHelper.TABLE_WATER_INTAKE_SUMMARY, cv, DatabaseHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_WATER_INTAKE_SUMMARY  , DatabaseHelper.ID + "=" + id, null);
    }

}

