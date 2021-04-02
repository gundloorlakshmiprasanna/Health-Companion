package com.example.healthcompanion;


import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HealthCompanion.db";

    public static final String TABLE_STEPS_SUMMARY = "StepsSummary";
    public static final String ID = "id";
    public static final String CREATION_DATE = "creationDate";
    public static final String STEPS_COUNT = "stepscount";

    public static final String TABLE_EXERCISE_SUMMARY = "EXERCISE_TIME";
    public static final String START = "startTime";
    public static final String STOP = "stopTime";


    public static final String TABLE_WATER_INTAKE_SUMMARY = "WATER_INTAKE";
    public static final String GLASS_COUNT = "glassCount";


    private static final String CREATE_TABLE_STEPS_SUMMARY = "create table "
            + TABLE_STEPS_SUMMARY + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CREATION_DATE + " TEXT, " + STEPS_COUNT + " INTEGER);";

    private static final String CREATE_TABLE_EXERCISE_SUMMARY = "create table " + TABLE_EXERCISE_SUMMARY + " (" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CREATION_DATE + " TEXT, " + START + " TEXT, " + STOP + " TEXT);";

    private static final String CREATE_TABLE_WATER_INTAKE_SUMMARY= "create table "
            + TABLE_WATER_INTAKE_SUMMARY + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CREATION_DATE + " TEXT, " + GLASS_COUNT + " INTEGER);";




    public DatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STEPS_SUMMARY);
        db.execSQL(CREATE_TABLE_EXERCISE_SUMMARY);
        db.execSQL(CREATE_TABLE_WATER_INTAKE_SUMMARY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_STEPS_SUMMARY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_SUMMARY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_INTAKE_SUMMARY);
        onCreate(db);
    }

    void addTime(String creationDate, String START, String STOP){

        START = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        STOP = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CREATION_DATE, creationDate);
        cv.put(START, START);
        cv.put(STOP, STOP);
        long result = db.insert(TABLE_EXERCISE_SUMMARY,null,cv);

        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show();
        }

    }

    void addRemainders(String creationDate, int glassCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CREATION_DATE, creationDate);
        cv.put(GLASS_COUNT, glassCount);
        long result = db.insert(TABLE_WATER_INTAKE_SUMMARY,null,cv);

        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show();
        }

    }



    public boolean createStepsEntry()
    {
        boolean isDateAlreadyPresent = false;
        boolean createSuccessful = false;
        int currentDateStepCounts = 0;
        Calendar mCalendar = Calendar.getInstance();
        String todayDate = String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH))+"/" +
                        String.valueOf(mCalendar.get(Calendar.MONTH))+"/"+String.valueOf(mCalendar.get(Calendar.YEAR));
        String selectQuery = "SELECT " + STEPS_COUNT + " FROM "
                + TABLE_STEPS_SUMMARY + " WHERE " + CREATION_DATE +" = '"+ todayDate+"'";
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    isDateAlreadyPresent = true;
                    currentDateStepCounts = c.getInt((c.getColumnIndex(STEPS_COUNT)));
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CREATION_DATE, todayDate);
            if(isDateAlreadyPresent)
            {
                values.put(STEPS_COUNT, ++currentDateStepCounts);
                int row = db.update(TABLE_STEPS_SUMMARY, values,
                        CREATION_DATE +" = '"+ todayDate+"'", null);
                if(row == 1)
                {
                    createSuccessful = true;
                }
                db.close();
            }
            else
            {
                values.put(STEPS_COUNT, 1);
                long row = db.insert(TABLE_STEPS_SUMMARY, null, values);
                if(row!=-1)
                {
                    createSuccessful = true;
                }
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createSuccessful;
    }


    public ArrayList<DateStepsModel> readStepsEntries()
    {
        ArrayList<DateStepsModel> mStepCountList = new ArrayList<DateStepsModel>();
        String selectQuery = "SELECT * FROM " + TABLE_STEPS_SUMMARY;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    DateStepsModel mDateStepsModel = new DateStepsModel();
                    mDateStepsModel.mDate = c.getString((c.getColumnIndex(CREATION_DATE)));
                    mDateStepsModel.mStepCount = c.getInt((c.getColumnIndex(STEPS_COUNT)));
                    mStepCountList.add(mDateStepsModel);
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mStepCountList;
    }

    public ArrayList<ExerciseModel> readExerciseData()
    {
        ArrayList<ExerciseModel> mExerciseList = new ArrayList<ExerciseModel>();
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_SUMMARY;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    ExerciseModel mExerciseModel = new ExerciseModel();
                    mExerciseModel.creationDate = c.getString((c.getColumnIndex(CREATION_DATE)));
                    mExerciseModel.START = c.getString((c.getColumnIndex(START)));
                    mExerciseModel.STOP = c.getString((c.getColumnIndex(STOP)));
                    mExerciseList.add(mExerciseModel);
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mExerciseList;
    }

    public ArrayList<WaterModel> readGlassCount()
    {
        ArrayList<WaterModel> mGlassCountList = new ArrayList<WaterModel>();
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_SUMMARY;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    WaterModel mWaterModel = new WaterModel();
                    mWaterModel.date = c.getString((c.getColumnIndex(CREATION_DATE)));
                    mWaterModel.glassCount = c.getString((c.getColumnIndex(GLASS_COUNT)));
                    mGlassCountList.add(mWaterModel);
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mGlassCountList;
    }
}