package com.kunal456k.moviedatabase.viewAdapters;

import static com.kunal456k.moviedatabase.constants.MovieDiffCallbackConstant.DIFF_CALLBACK;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieRecyclerItemLayoutBinding;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.interfaces.OnMovieClickListener;

import java.util.List;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder>{

    private final OnMovieClickListener onMovieClickListener;

    public MovieAdapter(@Nullable OnMovieClickListener onMovieClickListener){
        super(DIFF_CALLBACK);
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieRecyclerItemLayoutBinding binding = MovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MovieViewHolder(binding, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.setMovie(getItem(position));
    }

    public void update(List<Movie> movies){
        submitList(movies);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieRecyclerItemLayoutBinding binding;

        public MovieViewHolder(@NonNull MovieRecyclerItemLayoutBinding binding, OnMovieClickListener onMovieClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> onMovieClickListener.onMovieClick(binding.getMovie().getMovieId()));
        }

        public void setMovie(Movie movie) {
            binding.setMovie(movie);
        }
    }
}
