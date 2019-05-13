package com.example.manara.movies.View_Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.manara.movies.Favorite_Table;

import java.util.List;

public class View_Model_Movies extends AndroidViewModel {
    private LiveData<List<Favorite_Table>> favourite;

    public View_Model_Movies(@NonNull Application application) {
        super(application);

        Database database=Database.getInstance(this.getApplication());
     favourite=database.taskDao().loadAllMovies();

    }
    public LiveData<List<Favorite_Table>> getFavourate() {
        return favourite;
    }

}
