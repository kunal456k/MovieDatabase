package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    protected LiveData<String> status;
}
