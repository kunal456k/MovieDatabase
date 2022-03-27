package com.kunal456k.moviedatabase.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.MovieDatabaseApplication;
import com.kunal456k.moviedatabase.db.dao.BookmarkDao;
import com.kunal456k.moviedatabase.db.dao.CompanyDao;
import com.kunal456k.moviedatabase.db.dao.CompanyMovieCrossRefDao;
import com.kunal456k.moviedatabase.db.dao.CountryDao;
import com.kunal456k.moviedatabase.db.dao.CountryMovieCrossRefDao;
import com.kunal456k.moviedatabase.db.dao.GenreDao;
import com.kunal456k.moviedatabase.db.dao.GenreMovieCrossRefDao;
import com.kunal456k.moviedatabase.db.dao.LanguageDao;
import com.kunal456k.moviedatabase.db.dao.LanguageMovieCrossRefDao;
import com.kunal456k.moviedatabase.db.dao.MovieDao;
import com.kunal456k.moviedatabase.db.entities.Bookmark;
import com.kunal456k.moviedatabase.db.entities.Company;
import com.kunal456k.moviedatabase.db.entities.CompanyMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Country;
import com.kunal456k.moviedatabase.db.entities.CountryMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Genre;
import com.kunal456k.moviedatabase.db.entities.GenreMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Language;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.db.relations.MovieAndBookmark;
import com.kunal456k.moviedatabase.models.MovieApiResponse;
import com.kunal456k.moviedatabase.services.MovieApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MoviesRepository {
    private static final String TAG = MoviesRepository.class.getSimpleName();

    public final MutableLiveData<List<Movie>> trendingMovies = new MutableLiveData<>();
    public final MutableLiveData<List<Movie>> nowPlayingMovies = new MutableLiveData<>();
    public final MutableLiveData<String> failedTrendingStatus = new MutableLiveData<>();
    public final MutableLiveData<String> failedNowPlayingStatus = new MutableLiveData<>();
    public final MutableLiveData<Movie> movieDetailsLiveData = new MutableLiveData<>();
    public final MutableLiveData<List<Movie>> movieSearchLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> failedSearchStatus = new MutableLiveData<>();
    public final MutableLiveData<String> failedMovieDetailsStatus = new MutableLiveData<>();
    public final MutableLiveData<List<Movie>> bookmarkedMovies = new MutableLiveData<>();
    public final MutableLiveData<Boolean> bookMarkLockData = new MutableLiveData<>(false);

    private final MovieApi movieApi;

    @Inject MovieDao movieDao;
    @Inject GenreDao genreDao;
    @Inject GenreMovieCrossRefDao genreMovieCrossRefDao;
    @Inject LanguageDao languageDao;
    @Inject LanguageMovieCrossRefDao languageMovieCrossRefDao;
    @Inject CompanyDao companyDao;
    @Inject CompanyMovieCrossRefDao companyMovieCrossRefDao;
    @Inject CountryDao countryDao;
    @Inject CountryMovieCrossRefDao countryMovieCrossRefDao;
    @Inject BookmarkDao bookmarkDao;


    @Inject
    public MoviesRepository(MovieApi movieApi, Application application){
        this.movieApi = movieApi;
        ((MovieDatabaseApplication)application).getApplicationComponent().inject(this);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getTrending(){
        Observable<MovieApiResponse> observable = movieApi.getTrendingMovies();
        Observable<List<Movie>> listObservable = observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).map(MovieApiResponse::getMovies);
        listObservable.subscribe(this::onTrendingFetchSuccess, this::onTrendingFetchError);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getNowPlaying() {
        Observable<MovieApiResponse> responseObservable = movieApi.getNowPlayingMovies();
        responseObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .map(MovieApiResponse::getMovies).subscribe(this::onNowPlayingFetchSuccess, this::onNowPlayingFetchError);
    }

    public void getMovieDetails(int movieId) {
        getMovieDetailsFromDb(movieId);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void getMovieDetailsFromDb(int movieId) {
        failedMovieDetailsStatus.postValue("");
        Single<Movie> movieAndBookmarkSingle = movieDao.getMovieWithId(movieId);
        movieAndBookmarkSingle.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .subscribe(movie -> getMovieDetailsFromNetwork(movieId, movie),
                        throwable -> getMovieDetailsFromNetwork(movieId, null));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void getMovieDetailsFromNetwork(int movieId, Movie movie) {
        if (movie != null){
            MovieAndBookmark movieAndBookmark = movieDao.getMovieAndBookmarkWithMovie(movie.getMovieId());
            int bookMarkId = movieAndBookmark.bookmark == null ? 0 : movieAndBookmark.bookmark.getBookmarkId();
            movie.setBookmarkId(bookMarkId);
            failedMovieDetailsStatus.postValue("");
            movieDetailsLiveData.postValue(movie);
        }
        Observable<Movie> movieObservable = movieApi.getMovieDetails(movieId);
        movieObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .subscribe(this::onMovieDetailsSuccess, this::onMovieDetailsError);
    }

    private void onMovieDetailsSuccess(Movie movie) {
        insertMovieToDb(movie);
        MovieAndBookmark movieAndBookmark = movieDao.getMovieAndBookmarkWithMovie(movie.getMovieId());
        int bookMarkId = movieAndBookmark.bookmark == null ? 0 : movieAndBookmark.bookmark.getBookmarkId();
        movie.setBookmarkId(bookMarkId);
        failedMovieDetailsStatus.postValue("");
        movieDetailsLiveData.postValue(movie);
    }

    private void onMovieDetailsError(Throwable throwable) {
        Log.d(TAG, "onMovieDetailsError: unable to fetch movie details");
        movieDetailsLiveData.postValue(null);
        failedMovieDetailsStatus.postValue("Unable to load movie details, Please check internet connection");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void performSearch(String search) {
        if (search.isEmpty()){
            Log.d(TAG, "performSearch: remove search results");
            movieSearchLiveData.postValue(new ArrayList<>());
            return;
        }
        Observable<MovieApiResponse> responseObservable = movieApi.getSearchResponse(search, "en");
        responseObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .map(MovieApiResponse::getMovies).subscribe(this::onSearchSuccess, this::onSearchError);
    }

    private void onNowPlayingFetchError(Throwable t) {
        if (t == null){
            Log.d(TAG, "onNowPlayingFetchError: null response");
            failedNowPlayingStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onNowPlayingFetchError: error "+t.getMessage());
            t.printStackTrace();
            failedNowPlayingStatus.postValue("Error Connecting, Please check internet and retry");
        }
    }

    public void addMoviesToDatabase(List<Movie> movies){
        movies.forEach(this::insertMovieToDb);
    }

    private void insertMovieToDb(Movie movie) {
        movieDao.insertMovie(movie);
        if (movie.getGenres() != null) {
            movie.getGenres().forEach(genre -> insertMovieGenre(genre, movie));
        }
        if (movie.getLanguages() != null){
            movie.getLanguages().forEach(language -> insertMovieLanguage(language, movie));
        }
        if (movie.getProductionCompanies() != null){
            movie.getProductionCompanies().forEach(company -> insertMovieCompany(company, movie));
        }
        if (movie.getCountries() != null){
            movie.getCountries().forEach(country -> insertMovieCountry(country, movie));
        }
    }

    private void insertMovieCountry(Country country, Movie movie) {
        countryDao.insertCountry(country);
        countryMovieCrossRefDao.insertCountryMovieCrossRef(new CountryMovieCrossRef(country.getCountryName(), movie.getMovieId()));
    }

    private void insertMovieCompany(Company company, Movie movie) {
        companyDao.insertCompany(company);
        companyMovieCrossRefDao.insertCompanyMovieCrossRef(new CompanyMovieCrossRef(company.getCompanyId(), movie.getMovieId()));
    }

    private void insertMovieLanguage(Language language, Movie movie) {
        languageDao.insertLanguage(language);
        languageMovieCrossRefDao.insertLanguageMovieCrossRef(new LanguageMovieCrossRef(language.getLanguageName(), movie.getMovieId()));
    }


    private void insertMovieGenre(Genre genre, Movie movie) {
        genreDao.insertGenre(genre);
        genreMovieCrossRefDao.insertGenreMovieCrossRef(new GenreMovieCrossRef(genre.getGenreId(), movie.getMovieId()));
    }

    private void onNowPlayingFetchSuccess(List<Movie> movies) {
        if (movies == null){
            onNowPlayingFetchError(null);
            return;
        }
        Log.d(TAG, "onNowPlayingFetchSuccess: ");
        addMoviesToDatabase(movies);
        failedNowPlayingStatus.postValue("");
        nowPlayingMovies.postValue(movies);
    }

    private void onTrendingFetchSuccess(List<Movie> movies) {
        if (movies == null){
            onTrendingFetchError(null);
            return;
        }
        Log.d(TAG, "onTrendingFetchSuccess: ");
        addMoviesToDatabase(movies);
        failedTrendingStatus.postValue("");
        trendingMovies.postValue(movies);
    }

    private void onSearchSuccess(List<Movie> movies) {
        if (movies == null || movies.size() == 0){
            onSearchError(null);
            return;
        }
        addMoviesToDatabase(movies);
        failedSearchStatus.postValue("");
        Log.d(TAG, "onSearchSuccess: "+movies.size());
        movieSearchLiveData.postValue(movies);
    }

    private void onTrendingFetchError(Throwable t) {
        if (t == null){
            Log.d(TAG, "onTrendingFetchError: null response");
            failedTrendingStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onTrendingFetchError: error "+t.getMessage());
            t.printStackTrace();
            failedTrendingStatus.postValue("Error Connecting, Please check internet and retry");
        }
    }

    private void onSearchError(Throwable throwable) {
        if (throwable == null){
            Log.d(TAG, "onSearchError: null");
            failedSearchStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onSearchError: "+throwable.getMessage());
            throwable.printStackTrace();
            failedSearchStatus.postValue("Unable to search, Please check internet connection...");
        }
        movieSearchLiveData.postValue(new ArrayList<>());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getBookmarkedMovies() {
        Single<List<MovieAndBookmark>> movieAndBookmarkSingle = bookmarkDao.getAllBookmarkedMovieIds();
        movieAndBookmarkSingle.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .subscribe(this::onBookmarkLoad, this::onNoBookmarks);
    }

    private void onNoBookmarks(Throwable throwable) {
        bookmarkedMovies.postValue(new ArrayList<>());
    }

    private void onBookmarkLoad(List<MovieAndBookmark> movieAndBookmarks) {
        if (movieAndBookmarks == null || movieAndBookmarks.isEmpty()){
            onNoBookmarks(null);
            return;
        }
        List<Movie> movies = movieAndBookmarks.stream().map(movieAndBookmark -> {
            Movie movie = movieAndBookmark.movie;
            movie.setBookmarkId(movieAndBookmark.bookmark.getBookmarkId());
            return movie;
        }).collect(Collectors.toList());
        bookmarkedMovies.postValue(movies);
    }

    public void updateBookmark(Movie value, boolean isChecked) {
        Executors.newSingleThreadExecutor().execute(() -> {
            Log.d("test", "onCheckedChanged: 8");
            try {
                if (isChecked){
                    Log.d("test", "onCheckedChanged: 9");
                    Bookmark bookmark = new Bookmark();
                    bookmark.setMovieId(value.getMovieId());
                    bookmarkDao.insertBookmark(bookmark);
                }else {
                    Log.d("test", "onCheckedChanged: 10");
                    Bookmark bookmark = new Bookmark();
                    bookmark.setMovieId(value.getMovieId());
                    bookmark.setBookmarkId(value.getBookmarkId());
                    bookmarkDao.deleteBookmark(bookmark);
                }
                bookmarkUpdateComplete(value, isChecked);
            }catch (Exception e){
                e.printStackTrace();
                onBookmarkUpdateError(value);
            }
        });
    }

    private void onBookmarkUpdateError(Movie movie) {
        bookMarkLockData.setValue(false);
        movieDetailsLiveData.postValue(movie);
    }

    private void bookmarkUpdateComplete(Movie value, boolean isChecked) {
        Log.d("test", "onCheckedChanged: 11");
        if (isChecked){
            MovieAndBookmark movieAndBookmark = movieDao.getMovieAndBookmarkWithMovie(value.getMovieId());
            if (movieAndBookmark != null && movieAndBookmark.bookmark != null){
                value.setBookmarkId(movieAndBookmark.bookmark.getBookmarkId());
            }
        }else {
            value.setBookmarkId(0);
        }
        movieDetailsLiveData.postValue(value);
    }
}
