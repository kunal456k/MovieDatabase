package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentBookmarksBinding;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.viewAdapters.BookmarkedMoviesAdapter;
import com.kunal456k.moviedatabase.viewModels.BookmarkViewModel;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import java.util.List;

import javax.inject.Inject;

public class BookmarksFragment extends Fragment {

    @Inject BookmarkViewModel bookmarkViewModel;
    @Inject MovieNavigationViewModel movieNavigationViewModel;

    private BookmarkedMoviesAdapter adapter;

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomePage)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return init(inflater);
    }

    private View init(LayoutInflater inflater) {
        FragmentBookmarksBinding binding = FragmentBookmarksBinding.inflate(inflater);
        adapter = new BookmarkedMoviesAdapter(this::onMovieSelected, this::onBookmarkRemoved);
        binding.bookmarksRecyclerView.setAdapter(adapter);
        bookmarkViewModel.getBookmarkedMovies().observe(getViewLifecycleOwner(), this::onBookmarkMoviesChanges);
        return binding.getRoot();
    }

    private void onBookmarkRemoved(Movie movie) {
        bookmarkViewModel.removeBookmark(movie);
    }

    private void onBookmarkMoviesChanges(List<Movie> movies) {
        adapter.update(movies);
    }

    private void onMovieSelected(int movieId) {
        movieNavigationViewModel.setMovieId(movieId);
    }
}