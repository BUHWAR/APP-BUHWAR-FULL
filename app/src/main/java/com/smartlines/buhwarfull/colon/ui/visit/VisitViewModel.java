package com.smartlines.buhwarfull.colon.ui.visit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisitViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}