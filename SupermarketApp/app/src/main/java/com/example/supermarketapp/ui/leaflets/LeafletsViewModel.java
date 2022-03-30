package com.example.supermarketapp.ui.leaflets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeafletsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeafletsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}