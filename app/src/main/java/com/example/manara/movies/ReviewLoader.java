package com.example.manara.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewLoader extends AsyncTaskLoader <List<Reviews>> {
     public List <Reviews>reviewsList1=new ArrayList<>();
    int id;
    public ReviewLoader(@NonNull Context context,int id) {
        super(context);
        this.id=id;
    }

    @Nullable
    @Override
    public List<Reviews> loadInBackground() {
        List<Reviews>reviewsList=new ArrayList<>();
        GetReview getReview=new GetReview(id);
        try {
            reviewsList1=getReview.reviewsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reviewsList=getReview.reviewsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewsList;
    }


    public  List<Reviews>  doit()
    {
      return   loadInBackground();
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
