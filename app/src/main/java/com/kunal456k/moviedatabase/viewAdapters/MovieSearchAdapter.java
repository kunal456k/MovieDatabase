package com.kunal456k.moviedatabase.viewAdapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieSearchItemLayoutBinding;
import com.kunal456k.moviedatabase.interfaces.OnMovieClickListener;
import com.kunal456k.moviedatabase.models.MovieDetails;

import java.util.List;

import javax.inject.Inject;

public class MovieSearchAdapter extends ListAdapter<MovieDetails, MovieSearchAdapter.MovieSearchViewHolder> {

    private static final DiffUtil.ItemCallback<MovieDetails> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieDetails>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieDetails oldItem, @NonNull MovieDetails newItem) {
            return oldItem.getMovieId() == newItem.getMovieId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieDetails oldItem, @NonNull MovieDetails newItem) {
            return oldItem.getMovieId() == newItem.getMovieId() &&
                    oldItem.getRating() == newItem.getRating() &&
                    oldItem.getTitle().equals(newItem.getTitle());
        }
    };
    private final OnMovieClickListener onMovieClickListener;

    @Inject
    public MovieSearchAdapter(OnMovieClickListener onMovieClickListener) {
        super(DIFF_CALLBACK);
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieSearchItemLayoutBinding binding = MovieSearchItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MovieSearchViewHolder(binding, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchViewHolder holder, int position) {
        holder.binding.setMovie(getItem(position));
    }

    public void update(List<MovieDetails> movies){
        submitList(movies);
    }

    static class MovieSearchViewHolder extends RecyclerView.ViewHolder {

        private final MovieSearchItemLayoutBinding binding;

        public MovieSearchViewHolder(@NonNull MovieSearchItemLayoutBinding binding, OnMovieClickListener onMovieClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(v -> onMovieClickListener.onMovieClick(binding.getMovie().getMovieId()));
        }
    }
}
