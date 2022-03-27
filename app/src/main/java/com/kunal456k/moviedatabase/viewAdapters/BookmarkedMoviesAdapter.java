package com.kunal456k.moviedatabase.viewAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kunal456k.moviedatabase.constants.MovieDiffCallbackConstant;
import com.kunal456k.moviedatabase.databinding.BookmarkRecyclerItemLayoutBinding;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.interfaces.OnBookmarkRemovedListener;
import com.kunal456k.moviedatabase.interfaces.OnMovieClickListener;

import java.util.List;

public class BookmarkedMoviesAdapter extends ListAdapter<Movie, BookmarkedMoviesAdapter.BookmarkedMovieViewHolder>{

    private final OnMovieClickListener onMovieClickListener;
    private OnBookmarkRemovedListener onBookmarkRemovedListener;

    private List<Movie> movies;

    public BookmarkedMoviesAdapter(OnMovieClickListener onMovieClickListener, OnBookmarkRemovedListener onBookmarkRemovedListener) {
        super(MovieDiffCallbackConstant.DIFF_CALLBACK);
        this.onMovieClickListener = onMovieClickListener;
        this.onBookmarkRemovedListener = onBookmarkRemovedListener;
    }

    @NonNull
    @Override
    public BookmarkedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookmarkRecyclerItemLayoutBinding binding = BookmarkRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new BookmarkedMovieViewHolder(binding, onMovieClickListener, this::onBookmarkRemoved);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkedMovieViewHolder holder, int position) {
        holder.setMovie(getItem(position));
    }

    public void update(List<Movie> movies) {
        this.movies = movies;
        submitList(movies);
    }

    public void onBookmarkRemoved(int position) {
        if (position <0 || position >= getCurrentList().size()) return;
        Movie movie = movies.remove(position);
        onBookmarkRemovedListener.onBookmarkRemoved(movie);
        notifyItemRemoved(position);
    }

    static class BookmarkedMovieViewHolder extends RecyclerView.ViewHolder{
        private final BookmarkRecyclerItemLayoutBinding binding;

        public BookmarkedMovieViewHolder(@NonNull BookmarkRecyclerItemLayoutBinding binding,
                                         OnMovieClickListener onMovieClickListener, OnBookmarkRemoveSelectedListener onBookmarkRemoveSelectedListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> onMovieClickListener.onMovieClick(binding.getMovie().getMovieId()));
            binding.bookmarkToggle.setOnCheckedChangeListener((buttonView, isChecked) -> onBookmarkToggleChange(isChecked, onBookmarkRemoveSelectedListener));
        }

        private void onBookmarkToggleChange(boolean isChecked, OnBookmarkRemoveSelectedListener onBookmarkRemoveSelectedListener) {
            if (!isChecked){
                onBookmarkRemoveSelectedListener.onBookmarkRemoved(getAdapterPosition());
            }
        }

        public void setMovie(Movie movie) {
            binding.setMovie(movie);
        }
    }

    public interface OnBookmarkRemoveSelectedListener {
        void onBookmarkRemoved(int position);
    }
}
