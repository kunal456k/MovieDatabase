package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.MovieDatabaseApplication;
import com.kunal456k.moviedatabase.modules.AppModule;
import com.kunal456k.moviedatabase.modules.DatabaseModule;
import com.kunal456k.moviedatabase.modules.NetworkModule;
import com.kunal456k.moviedatabase.modules.WorkerModule;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class, WorkerModule.class, DatabaseModule.class})
public interface ApplicationComponent{

    HomeComponent getHomeComponent();

    void inject(MovieDatabaseApplication movieDatabaseApplication);
    void inject(MoviesRepository moviesRepository);
}
