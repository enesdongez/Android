package com.example.neekilir.ui.home;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.neekilir.AdminGiris;
import com.example.neekilir.Anasayfa;
import com.example.neekilir.Anasayfa_Activity;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");

    }



    public LiveData<String> getText() {
        return mText;
    }
}