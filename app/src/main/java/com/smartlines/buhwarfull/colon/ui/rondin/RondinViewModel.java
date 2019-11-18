package com.smartlines.buhwarfull.colon.ui.rondin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RondinViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RondinViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}