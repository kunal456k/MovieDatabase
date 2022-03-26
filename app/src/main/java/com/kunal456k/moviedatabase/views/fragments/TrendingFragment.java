package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentTrendingBinding;
import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.viewAdapters.MovieAdapter;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.viewModels.TrendingViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import java.util.List;

import javax.inject.Inject;


public class TrendingFragment extends Fragment {
    private static final String TAG = TrendingFragment.class.getSimpleName();

    @Inject TrendingViewModel trendingViewModel;
    @Inject MovieNavigationViewModel movieNavigationViewModel;

    private MovieAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomePage)requireActivity()).getHomeComponent().inject(this);
    }

    public TrendingFragment() {
        // Required empty public constructor
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
        FragmentTrendingBinding binding = FragmentTrendingBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        binding.setTrendingViewModel(trendingViewModel);
        adapter = new MovieAdapter(this::onMovieSelected);
        binding.trendingNowRecycler.setAdapter(adapter);
        trendingViewModel.getTrendingMovies().observe(getViewLifecycleOwner(), this::onTrendingMoviesUpdate);
        return binding.getRoot();
    }

    private void onMovieSelected(int movieId) {
        Log.d(TAG, "onMovieSelected: "+movieId);
        movieNavigationViewModel.setMovieId(movieId);
    }

    private void onTrendingMoviesUpdate(List<MovieDetails> movies) {
        adapter.update(movies);
    }
}