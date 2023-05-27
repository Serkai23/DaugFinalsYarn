package com.example.ifitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Exercise.db";

    private static final String TABLE_EXERCISES = "exercises";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EXERCISE_NAME = "exercise_name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";

    public ExerciseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXERCISES_TABLE = "CREATE TABLE " + TABLE_EXERCISES +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_EXERCISE_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_IMAGE + " INTEGER" +
                ")";
        db.execSQL(CREATE_EXERCISES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(db);
    }

    public void addFavoriteExercise(WarmUpExercise exercise) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISE_NAME, exercise.getName());
        values.put(COLUMN_DESCRIPTION, exercise.getDescription());
        values.put(COLUMN_IMAGE, exercise.getImageResId());

        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    public void removeFavoriteExercise(WarmUpExercise exercise) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EXERCISES, COLUMN_EXERCISE_NAME + " = ?", new String[]{exercise.getName()});
        db.close();
    }

    public List<WarmUpExercise> getFavoriteExercises() {
        List<WarmUpExercise> favoriteExercises = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXERCISES, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String exerciseName = cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_NAME));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            int imageResId = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));

            WarmUpExercise exercise = new WarmUpExercise(exerciseName, description, imageResId);
            favoriteExercises.add(exercise);
        }

        cursor.close();
        db.close();

        return favoriteExercises;
    }
}
