package com.example.manara.movies.View_Model;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class FavoriteVMFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;


    public FavoriteVMFactory(Application application, int id) {
        mApplication = application;
        mParam = id;
    }

    @NonNull
    @Override
    public <T extends android.arch.lifecycle.ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewModel(mApplication, mParam);
    }
}