package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.kunal456k.moviedatabase.databinding.FragmentMovieDetailsBinding;
import com.kunal456k.moviedatabase.viewModels.MovieDetailsViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import javax.inject.Inject;

public class MovieDetailsFragment extends Fragment {

    @Inject MovieDetailsViewModel movieDetailsViewModel;

    private FragmentMovieDetailsBinding binding;

    public MovieDetailsFragment() {
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

    private View init(LayoutInflater inflater) {
        binding = FragmentMovieDetailsBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        binding.setMovieDetailsViewModel(movieDetailsViewModel);
        return binding.getRoot();
    }
}