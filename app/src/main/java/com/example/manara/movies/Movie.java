package com.example.manara.movies;

public class Movie {

   private String name;
   private String poster;
   private String review;
   private Number vote;
   private String date;
   private int id;
    public Number getVote() {
        return vote;
    }
    public void setVote(Number vote) {
        this.vote = vote;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
