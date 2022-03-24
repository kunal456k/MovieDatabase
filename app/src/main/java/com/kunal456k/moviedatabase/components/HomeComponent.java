package com.kunal456k.moviedatabase.components;

import com.kunal456k.moviedatabase.views.fragments.BookmarksFragment;
import com.kunal456k.moviedatabase.views.fragments.BottomNavigationFragment;
import com.kunal456k.moviedatabase.views.activity.HomeActivity;
import com.kunal456k.moviedatabase.views.fragments.NowPlayingFragment;
import com.kunal456k.moviedatabase.views.fragments.SearchFragment;
import com.kunal456k.moviedatabase.views.fragments.TrendingFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface HomeComponent {

    void inject(HomeActivity homeActivity);
    void inject(BottomNavigationFragment bottomNavigationFragment);
    void inject(SearchFragment searchFragment);
    void inject(BookmarksFragment bookmarksFragment);
    void inject(TrendingFragment trendingFragment);
    void inject(NowPlayingFragment nowPlayingFragment);
}
