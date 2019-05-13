package com.example.manara.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrailerLoader extends AsyncTaskLoader<List<Trailer>> {

int id ;
public TrailerLoader(@NonNull Context context,int id) {
        super(context);
        this.id=id;



    }
//open the connection by using GetTrailer Class
    @Nullable
    @Override
    public List<Trailer>  loadInBackground() {

        List<Trailer>trailerList=new ArrayList<>();
        GetTrailer getTrailer=new GetTrailer(id);
        try {
            trailerList=getTrailer.trailerList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trailerList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
