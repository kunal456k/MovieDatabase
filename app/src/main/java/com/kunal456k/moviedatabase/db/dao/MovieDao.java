package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kunal456k.moviedatabase.db.entities.Bookmark;
import com.kunal456k.moviedatabase.db.entities.Company;
import com.kunal456k.moviedatabase.db.entities.CompanyMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.CountryMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Genre;
import com.kunal456k.moviedatabase.db.entities.GenreMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Language;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.db.relations.MovieAndBookmark;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompany(Company company);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenre(Genre genre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLanguage(Language language);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookmark(Bookmark bookmark);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompanyMovie(CompanyMovieCrossRef companyMovieCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountMovie(CountryMovieCrossRef countryMovieCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenreMovie(GenreMovieCrossRef genreMovieCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLanguageMovie(LanguageMovieCrossRef languageMovieCrossRef);

    @Transaction
    @Query("SELECT * FROM movie")
    List<Movie> getAllMovies();

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movie LIMIT 1")
    MovieAndBookmark getMovieAndBookmarkWithMovie(int movie);
}
