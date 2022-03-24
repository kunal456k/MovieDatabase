package com.kunal456k.moviedatabase.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieRecyclerItemLayoutBinding;
import com.kunal456k.moviedatabase.helpers.MovieDiffUtillCallback;
import com.kunal456k.moviedatabase.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieRecyclerItemLayoutBinding binding = MovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void update(List<Movie> movies){
        MovieDiffUtillCallback myDiffUtillCallback = new MovieDiffUtillCallback(this.movies, movies);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtillCallback);
        this.movies = movies;
        diffResult.dispatchUpdatesTo(this);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieRecyclerItemLayoutBinding binding;

        public MovieViewHolder(@NonNull MovieRecyclerItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
