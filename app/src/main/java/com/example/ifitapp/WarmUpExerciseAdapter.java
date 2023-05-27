package com.example.ifitapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WarmUpExerciseAdapter extends RecyclerView.Adapter<WarmUpExerciseAdapter.ExerciseViewHolder> {

    private List<WarmUpExercise> warmUpExercises;
    private WarmUpExerciseClickListener exerciseClickListener;

    public WarmUpExerciseAdapter(List<WarmUpExercise> warmUpExercises, WarmUpExerciseClickListener exerciseClickListener) {
        this.warmUpExercises = warmUpExercises;
        this.exerciseClickListener = exerciseClickListener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warmup_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        WarmUpExercise exercise = warmUpExercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return warmUpExercises.size();
    }

    public interface WarmUpExerciseClickListener {
        void onExerciseClick(int position);

        void onExerciseFavoriteClick(int position);
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageExercise;
        private ImageButton btnAddToFavorites;
        private TextView textExerciseName;
        private TextView textExerciseDescription;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageExercise = itemView.findViewById(R.id.imageExercise);
            btnAddToFavorites = itemView.findViewById(R.id.btnAddToFavorites);
            textExerciseName = itemView.findViewById(R.id.textExerciseName);
            textExerciseDescription = itemView.findViewById(R.id.textExerciseDescription);
        }

        public void bind(WarmUpExercise exercise) {
            imageExercise.setImageResource(exercise.getImageResId());
            textExerciseName.setText(exercise.getName());
            textExerciseDescription.setText(exercise.getDescription());

            // Handle add to favorites button click event
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        exerciseClickListener.onExerciseFavoriteClick(position);
                    }
                }
            });

            // Handle exercise item click event
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        exerciseClickListener.onExerciseClick(position);
                    }
                }
            });

            // Set the appropriate favorite icon based on the exercise's favorite state
            if (exercise.isFavorite()) {
                btnAddToFavorites.setImageResource(R.drawable.ic_favorite);
            } else {
                btnAddToFavorites.setImageResource(R.drawable.ic_unfavorite);
            }
        }
    }
}
