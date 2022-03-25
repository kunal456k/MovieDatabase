package com.kunal456k.moviedatabase.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.databinding.MovieRecyclerItemLayoutBinding;
import com.kunal456k.moviedatabase.models.Movie;

import java.util.List;

import javax.inject.Inject;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {

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

    @Inject
    public MovieAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieRecyclerItemLayoutBinding binding = MovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.setMovie(getItem(position));
    }

    public void update(List<Movie> movies){
        submitList(movies);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieRecyclerItemLayoutBinding binding;

        public MovieViewHolder(@NonNull MovieRecyclerItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
