package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentSearchBinding;
import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.viewAdapters.MovieSearchAdapter;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.viewModels.SearchViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();

    @Inject SearchViewModel searchViewModel;
    @Inject MovieNavigationViewModel movieNavigationViewModel;

    private MovieSearchAdapter movieSearchAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomePage)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return init(inflater);
    }

    @NonNull
    private View init(LayoutInflater inflater) {
        FragmentSearchBinding binding = FragmentSearchBinding.inflate(inflater);
        binding.setSearchViewModel(searchViewModel);
        binding.setLifecycleOwner(this);
        movieSearchAdapter = new MovieSearchAdapter(this::onMovieSelected);
        binding.searchRecyclerView.setAdapter(movieSearchAdapter);
        binding.etMovieSearch.setText(searchViewModel.getSearchInputData().getValue());
        searchViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), this::onSearchListChanged);
        return binding.getRoot();
    }

    private void onMovieSelected(int movieId) {
        Log.d(TAG, "onMovieSelected: "+movieId);
        movieNavigationViewModel.setMovieId(movieId);
    }

    private void onSearchListChanged(List<MovieDetails> movies) {
        movieSearchAdapter.update(movies);
    }
}