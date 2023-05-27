package com.example.ifitapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifitapp.ExerciseDbHelper;
import com.example.ifitapp.FavoriteExerciseAdapter;
import com.example.ifitapp.R;
import com.example.ifitapp.WarmUpExercise;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteExerciseAdapter exerciseAdapter;
    private List<WarmUpExercise> favoriteExercises;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create an adapter for the RecyclerView
        exerciseAdapter = new FavoriteExerciseAdapter(favoriteExercises);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(exerciseAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the list of favorite exercises when the fragment is resumed
        favoriteExercises = getFavoriteExercises();
        exerciseAdapter.setExercises(favoriteExercises);
        exerciseAdapter.notifyDataSetChanged();
    }

    // Replace this method with your logic to fetch the favorite exercises
    private List<WarmUpExercise> getFavoriteExercises() {
        // Retrieve favorite exercises from the database or any other data source
        ExerciseDbHelper dbHelper = new ExerciseDbHelper(getContext());
        return dbHelper.getFavoriteExercises();
    }
}
