package com.kunal456k.moviedatabase.modules;

import android.app.Application;

import androidx.annotation.NonNull;

import com.kunal456k.moviedatabase.db.MovieDatabase;
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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    @Provides
    @Singleton
    MovieDatabase providesMovieDatabase(Application application){
        return MovieDatabase.getInstance(application);
    }

    @Provides
    @Singleton
    MovieDao providesMovieDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.movieDao();
    }

    @Provides
    @Singleton
    GenreDao providesGenreDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.genreDao();
    }

    @Provides
    @Singleton
    GenreMovieCrossRefDao providesGenreMovieCrossRefDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.genreMovieCrossRefDao();
    }

    @Provides
    @Singleton
    LanguageDao providesLanguageDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.languageDao();
    }

    @Provides
    @Singleton
    LanguageMovieCrossRefDao providesLanguageMovieCrossRefDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.languageMovieCrossRefDao();
    }

    @Provides
    @Singleton
    CompanyDao providesCompanyDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.companyDao();
    }

    @Provides
    @Singleton
    CompanyMovieCrossRefDao providesCompanyMovieCrossRefDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.companyMovieCrossRefDao();
    }

    @Provides
    @Singleton
    CountryDao providesCountryDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.countryDao();
    }

    @Provides
    @Singleton
    CountryMovieCrossRefDao providesCountryMovieCrossRefDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.countryMovieCrossRefDao();
    }

    @Provides
    @Singleton
    BookmarkDao providesBookmarkDao(@NonNull MovieDatabase movieDatabase){
        return movieDatabase.bookmarkDao();
    }
}
