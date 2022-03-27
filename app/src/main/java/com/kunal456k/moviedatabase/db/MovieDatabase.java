package com.kunal456k.moviedatabase.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

@Database(entities = {Movie.class, Bookmark.class,
        Language.class, Genre.class, Country.class,
        Company.class, CompanyMovieCrossRef.class,
        CountryMovieCrossRef.class, GenreMovieCrossRef.class,
        LanguageMovieCrossRef.class},
        version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "movie_db";
    private static volatile MovieDatabase instance = null;

    public abstract MovieDao movieDao();

    public static MovieDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MovieDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
