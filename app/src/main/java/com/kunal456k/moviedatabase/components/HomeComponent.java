package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.views.fragments.BookmarksFragment;
import com.kunal456k.moviedatabase.views.fragments.BottomNavigationFragment;
import com.kunal456k.moviedatabase.views.activity.HomePage;
import com.kunal456k.moviedatabase.views.fragments.HomeNavigationFragment;
import com.kunal456k.moviedatabase.views.fragments.MovieDetailsFragment;
import com.kunal456k.moviedatabase.views.fragments.NowPlayingFragment;
import com.kunal456k.moviedatabase.views.fragments.SearchFragment;
import com.kunal456k.moviedatabase.views.fragments.TrendingFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface HomeComponent {

    void inject(HomePage homePage);
    void inject(BottomNavigationFragment bottomNavigationFragment);
    void inject(SearchFragment searchFragment);
    void inject(BookmarksFragment bookmarksFragment);
    void inject(TrendingFragment trendingFragment);
    void inject(NowPlayingFragment nowPlayingFragment);
    void inject(HomeNavigationFragment homeNavigationFragment);
    void inject(MovieDetailsFragment movieDetailsFragment);
}
