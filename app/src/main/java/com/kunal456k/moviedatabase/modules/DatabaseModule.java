package com.kunal456k.moviedatabase.modules;

import android.app.Application;

import com.kunal456k.moviedatabase.db.MovieDatabase;
import com.kunal456k.moviedatabase.db.dao.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MovieDao providesMovieDao(Application application){
        return MovieDatabase.getInstance(application).movieDao();
    }

}
