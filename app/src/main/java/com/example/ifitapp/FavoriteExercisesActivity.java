package com.example.ifitapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteExercisesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoriteExerciseAdapter exerciseAdapter;
    private List<WarmUpExercise> favoriteExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_exercises);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteExercises = (List<WarmUpExercise>) getIntent().getSerializableExtra("favoriteExercises");

        exerciseAdapter = new FavoriteExerciseAdapter(favoriteExercises);
        recyclerView.setAdapter(exerciseAdapter);
    }
}

