package com.example.manara.movies.View_Model;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;



import com.example.manara.movies.Favorite_Table;
import com.example.manara.movies.TaskDao;



@android.arch.persistence.room.Database(entities = {Favorite_Table.class}, version = 1, exportSchema = false)
@TypeConverters(Type_Convertor.class)

public abstract class Database extends RoomDatabase {
    static String database_name="favoritemovie";
    public static Database instance;
    public static Database getInstance(Context context) {
        if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, Database.database_name)
                        .build();

        }
        return instance;
    }

    public abstract TaskDao taskDao();

}
