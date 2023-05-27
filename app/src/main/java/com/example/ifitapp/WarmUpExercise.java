package com.example.ifitapp;

import android.content.Context;

public class WarmUpExercise {
    private String name;
    private String description;
    private int imageResId;
    private boolean isFavorite;

    public WarmUpExercise(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.isFavorite = false; // Initialize as not favorite by default
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void addFavoriteExercise(Context context) {
        ExerciseDbHelper dbHelper = new ExerciseDbHelper(context);
        dbHelper.addFavoriteExercise(this);
    }

    public void removeFavoriteExercise(Context context) {
        ExerciseDbHelper dbHelper = new ExerciseDbHelper(context);
        dbHelper.removeFavoriteExercise(this);
    }

    public int getImage() {
        return imageResId;
    }
}
