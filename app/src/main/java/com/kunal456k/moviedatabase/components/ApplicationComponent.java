package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.modules.AppModule;
import com.kunal456k.moviedatabase.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent{

    HomeComponent getHomeComponent();

}
