package com.smartlines.buhwarfull.colon.ui.helper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelperViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelperViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}