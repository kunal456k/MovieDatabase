package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentNowPlayingBinding;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.viewModels.NowPlayingViewModel;
import com.kunal456k.moviedatabase.views.activity.HomeActivity;
import com.kunal456k.moviedatabase.viewAdapters.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


public class NowPlayingFragment extends Fragment {

    @Inject NowPlayingViewModel nowPlayingViewModel;
    @Inject MovieAdapter adapter;

    private FragmentNowPlayingBinding binding;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNowPlayingBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setNowPlayingViewModel(nowPlayingViewModel);
        binding.playingNowRecycler.setAdapter(adapter);
        nowPlayingViewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), this::onNowPlayingMoviesUpdate);
    }

    private void onNowPlayingMoviesUpdate(List<Movie> movies) {
        adapter.update(movies);
    }
}