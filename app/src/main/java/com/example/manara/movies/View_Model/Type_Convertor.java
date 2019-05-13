package com.example.manara.movies.View_Model;

import android.arch.persistence.room.TypeConverter;


import com.example.manara.movies.Reviews;
import com.example.manara.movies.Trailer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class Type_Convertor {



    public static Gson gson = new Gson();

    @TypeConverter
    public static String movieToString(List<Trailer> arrayList){
        return gson.toJson(arrayList);
    }

    @TypeConverter
    public static List<Trailer> stringToMovie(String movie){
        Type listType = new TypeToken<List<Trailer>>() {}.getType();
        return gson.fromJson(movie , listType);
    }

    @TypeConverter
    public static String reviewToString(List<Reviews> arrayList){
        return gson.toJson(arrayList);
    }

    @TypeConverter
    public static List<Reviews> stringToReview(String movie){
        Type listType = new TypeToken<List<Reviews>>() {}.getType();
        return gson.fromJson(movie , listType);
    }
}