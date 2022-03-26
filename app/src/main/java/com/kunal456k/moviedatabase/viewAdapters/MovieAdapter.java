package com.kunal456k.moviedatabase.viewAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieRecyclerItemLayoutBinding;
import com.kunal456k.moviedatabase.interfaces.OnMovieClickListener;
import com.kunal456k.moviedatabase.models.MovieDetails;

import java.util.List;

import javax.inject.Inject;

public class MovieAdapter extends ListAdapter<MovieDetails, MovieAdapter.MovieViewHolder>{

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
        holder.binding.setMovie(getItem(position));
    }

    public void update(List<MovieDetails> movies){
        submitList(movies);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieRecyclerItemLayoutBinding binding;

        public MovieViewHolder(@NonNull MovieRecyclerItemLayoutBinding binding, OnMovieClickListener onMovieClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> onMovieClickListener.onMovieClick(binding.getMovie().getMovieId()));
        }
    }
}
