package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentNowPlayingBinding;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.viewModels.NowPlayingViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;
import com.kunal456k.moviedatabase.viewAdapters.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


public class NowPlayingFragment extends Fragment {
    private static final String TAG = NowPlayingFragment.class.getSimpleName();

    @Inject NowPlayingViewModel nowPlayingViewModel;
    @Inject MovieNavigationViewModel movieNavigationViewModel;

    private MovieAdapter adapter;

    public NowPlayingFragment() {
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
        FragmentNowPlayingBinding binding = FragmentNowPlayingBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        binding.setNowPlayingViewModel(nowPlayingViewModel);
        adapter = new MovieAdapter(this::onMovieSelected);
        binding.playingNowRecycler.setAdapter(adapter);
        nowPlayingViewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), this::onNowPlayingMoviesUpdate);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void onMovieSelected(int movieId) {
        Log.d(TAG, "onMovieSelected: "+movieId);
        movieNavigationViewModel.setMovieId(movieId);
    }

    private void onNowPlayingMoviesUpdate(List<Movie> movies) {
        adapter.update(movies);
    }
}