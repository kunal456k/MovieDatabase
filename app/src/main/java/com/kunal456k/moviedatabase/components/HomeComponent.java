package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.views.BookmarksFragment;
import com.kunal456k.moviedatabase.views.BottomNavigationFragment;
import com.kunal456k.moviedatabase.views.HomeActivity;
import com.kunal456k.moviedatabase.views.SearchFragment;
import com.kunal456k.moviedatabase.views.TrendingFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface HomeComponent {

    void inject(HomeActivity homeActivity);
    void inject(BottomNavigationFragment bottomNavigationFragment);
    void inject(SearchFragment searchFragment);
    void inject(TrendingFragment trendingFragment);
    void inject(BookmarksFragment bookmarksFragment);
}
