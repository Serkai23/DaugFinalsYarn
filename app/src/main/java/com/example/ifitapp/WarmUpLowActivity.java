package com.example.ifitapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WarmUpLowActivity extends AppCompatActivity implements WarmUpExerciseAdapter.WarmUpExerciseClickListener {

    private RecyclerView recyclerView;
    private Context context;
    private WarmUpExerciseAdapter exerciseAdapter;
    private List<WarmUpExercise> warmUpExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup_low);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list of warm-up exercises
        warmUpExercises = getWarmUpExercises();

        // Create an adapter for the RecyclerView
        exerciseAdapter = new WarmUpExerciseAdapter(warmUpExercises, this);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(exerciseAdapter);

        // Assign the context
        context = this;
    }

    @Override
    public void onExerciseClick(int position) {
        // Handle exercise click event here
        WarmUpExercise exercise = warmUpExercises.get(position);
        // Implement your logic for handling the click event, such as opening a detail screen
    }

    @Override
    public void onExerciseFavoriteClick(int position) {
        // Handle exercise favorite/unfavorite click event here
        WarmUpExercise exercise = warmUpExercises.get(position);
        exercise.setFavorite(!exercise.isFavorite());
        exerciseAdapter.notifyItemChanged(position);

        if (exercise.isFavorite()) {
            exercise.addFavoriteExercise(context); // Save to database
            showToast("Added to favorites");
        } else {
            exercise.removeFavoriteExercise(context); // Remove from database
            showToast("Removed from favorites");
        }
    }

    // Replace this method with your logic to fetch the warm-up exercises
    private List<WarmUpExercise> getWarmUpExercises() {
        // TODO: Fetch warm-up exercises from your data source
        // and return a list of WarmUpExercise objects
        // Example:
        List<WarmUpExercise> exercises = new ArrayList<>();
        exercises.add(new WarmUpExercise("Jumping-Jacks", "Jumping Jacks are a full-body exercise that involves jumping while spreading the legs and raising the arms overhead. It helps to increase heart rate, warm up the muscles, and improve coordination.", R.mipmap.exercise1));
        exercises.add(new WarmUpExercise("Arm Swings", "Arm Swings are a dynamic stretching exercise that targets the upper body. Stand with your feet shoulder-width apart and swing your arms forward and backward in a controlled motion. It helps to loosen up the shoulder joints and improve flexibility in the arms.", R.mipmap.exercise2));
        exercises.add(new WarmUpExercise("Squats", "Squats are a lower body exercise that targets the muscles of the legs and buttocks. Stand with your feet shoulder-width apart, bend your knees, and lower your hips down as if sitting back into a chair. Squats help to strengthen the quadriceps, hamstrings, and gluteal muscles.", R.mipmap.exercise3));
        return exercises;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




