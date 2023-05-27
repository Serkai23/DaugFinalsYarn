package com.example.ifitapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifitapp.WarmUpExercise;

import java.util.List;

public class FavoriteExerciseAdapter extends RecyclerView.Adapter<FavoriteExerciseAdapter.ExerciseViewHolder> {

    private List<WarmUpExercise> exercises;

    public FavoriteExerciseAdapter(List<WarmUpExercise> exercises) {
        this.exercises = exercises;
    }

    public void setExercises(List<WarmUpExercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        WarmUpExercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder implementation

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            // ViewHolder initialization
        }

        void bind(WarmUpExercise exercise) {
            // Bind exercise data to the ViewHolder views
        }
    }
}

