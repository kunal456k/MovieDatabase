package com.kunal456k.moviedatabase.viewAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieSearchItemLayoutBinding;
import com.kunal456k.moviedatabase.interfaces.OnMovieClickListener;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

import javax.inject.Inject;

public class MovieSearchAdapter extends ListAdapter<Movie, MovieSearchAdapter.MovieSearchViewHolder> {

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieId() == newItem.getMovieId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
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

    public void update(List<Movie> movies){
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
