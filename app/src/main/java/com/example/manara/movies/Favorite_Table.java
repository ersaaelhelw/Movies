package com.example.manara.movies;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Favorite")
public class Favorite_Table {
    @PrimaryKey
    int id;
    String poster;
    String name;
    String vote;
    String date;
    String overview;
    List<Trailer>trailerList;
    List<Reviews>reviewsList;

     public Favorite_Table(int id, String posterPath, String name, String date, String vote, String overView, List<Trailer>trailerList,  List<Reviews>reviewsList)
    {
        this.id=id;
        this.date=date;
        this.name=name;
        this.overview=overView;
        this.poster=posterPath;
        this.reviewsList=reviewsList;
        this.trailerList=trailerList;
        this.vote=vote;
    }
    @Ignore
    public Favorite_Table( String posterPath, String name, String date, String vote, String overView, List<Trailer>trailerList,  List<Reviews>reviewsList)
    {
        this.date=date;
        this.name=name;
        this.overview=overView;
        this.poster=posterPath;
        this.reviewsList=reviewsList;
        this.trailerList=trailerList;
        this.vote=vote;

    }
   public Favorite_Table(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(    String vote
    ) {
        this.vote = vote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }
}

