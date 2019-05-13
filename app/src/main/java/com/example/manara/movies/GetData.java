package com.example.manara.movies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    public List<Movie>movieList=new ArrayList<>();
    Movie movie=new Movie();
    String data ="";
    String t="top_rated";
    URL url= null;
   public GetData(URL url1)
{
 url=url1;
}
    public List<Movie> movie_list() throws IOException {
        //read url
        try {
            //make connection to get data
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            // read data by input stream
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedInputStream= new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line!=null)
            {
                line =bufferedInputStream.readLine();
                data+=line;
            }


            JSONObject result = new JSONObject(data);
            JSONArray list=  result.getJSONArray("results");

            JSONObject jsonObject;

            for(int i=0; i<list.length();i++)
            {
                jsonObject=(JSONObject) list.get(i);
                String link="http://image.tmdb.org/t/p/w780/";
                movie.setName((String)jsonObject.get("title"));
                link+=jsonObject.get("poster_path");
                movie.setPoster(link);

                movie.setReview((String) jsonObject.get("overview"));
                movie.setVote((Number) jsonObject.get("vote_average"));
                movie.setDate((String)jsonObject.get("release_date"));
                movie.setId((Integer) jsonObject.get("id"));
                //release_date
                movieList.add(movie);
                movie=new Movie();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return movieList;
    }


}
