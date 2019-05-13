package com.example.manara.movies.View_Model;


import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.manara.movies.Favorite_Table;

//get all movie in table
public class ViewModel extends android.arch.lifecycle.ViewModel {

  private   LiveData<Favorite_Table> taskentry;
  private Application application;
    public ViewModel(Application application, int id)
    {
        this.application = application;
        Database database = Database.getInstance(application);
        taskentry=database.taskDao().loadTaskById(id);
    }


    public LiveData<Favorite_Table> getTaskEntry() {
        return taskentry;
    }

//    public void removeFavorite(Favorite_Table favorite_table) {
//        Database database = Database.getInstance(application);
//        database.taskDao().delete(favorite_table);
//    }

}
