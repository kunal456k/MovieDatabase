package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.MovieDatabaseApplication;
import com.kunal456k.moviedatabase.modules.AppModule;
import com.kunal456k.moviedatabase.modules.NetworkModule;
import com.kunal456k.moviedatabase.modules.WorkerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class, WorkerModule.class})
public interface ApplicationComponent{

    HomeComponent getHomeComponent();

    void inject(MovieDatabaseApplication movieDatabaseApplication);
}
