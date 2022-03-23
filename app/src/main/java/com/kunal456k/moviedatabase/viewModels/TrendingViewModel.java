package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.models.NowPlayingMovies;
import com.kunal456k.moviedatabase.models.TrendingMovies;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Inject;

public class TrendingViewModel extends BaseViewModel {

    private final MutableLiveData<TrendingMovies> trendingMovies = new MutableLiveData<>();
    private final MutableLiveData<NowPlayingMovies> nowPlaying = new MutableLiveData<>();

    private MoviesRepository moviesRepository;

    @Inject
    public TrendingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public MutableLiveData<TrendingMovies> getTrendingMovies() {
        return trendingMovies;
    }

    public MutableLiveData<NowPlayingMovies> getNowPlaying() {
        return nowPlaying;
    }
}
