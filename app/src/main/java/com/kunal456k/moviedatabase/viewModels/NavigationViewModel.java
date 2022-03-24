package com.kunal456k.moviedatabase.viewModels;

import android.util.Log;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.navigation.NavigationBarView;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.components.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class NavigationViewModel extends ViewModel implements NavigationBarView.OnItemSelectedListener{
    private static final String TAG = NavigationViewModel.class.getSimpleName();

    private final MutableLiveData<Integer> navigationId = new MutableLiveData<>();
    private final MutableLiveData<Boolean> exitPress = new MutableLiveData<>(false);

    @Inject
    public NavigationViewModel(){

    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (navigationId.getValue() == null || navigationId.getValue() != R.id.navigation_home){
                navigationId.setValue(R.id.navigation_home);
            }else {
                exitPress.setValue(true);
            }
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected: "+item.getTitle());
        navigationId.setValue(item.getItemId());
        return true;
    }

    public MutableLiveData<Integer> getNavigationId() {
        return navigationId;
    }

    public OnBackPressedCallback getOnBackPressCallBack(){
        return onBackPressedCallback;
    }

    public MutableLiveData<Boolean> getExitPress() {
        return exitPress;
    }
}
