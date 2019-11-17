package com.smarltines.buhwarfull.guard.ui.visitor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisitorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisitorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}