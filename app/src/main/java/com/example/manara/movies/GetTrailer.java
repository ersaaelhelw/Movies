package com.example.manara.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetTrailer {

int id;
   public GetTrailer(int id)
   {
       this.id=id;

   }

    public List<Trailer> trailerList=new ArrayList<>();
    Trailer trailer=new Trailer();
    String data ="";
    URL url= null;
    public List<Trailer> trailerList() throws IOException {
        //read url

        try {
            url = new URL("http://api.themoviedb.org/3/movie/"+id+"/videos?api_key=f236e35beb4f4914e7e13dc23b4b1fc4");

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
                trailer.setKey((String)jsonObject.get("key"));
                trailer.setName((String)jsonObject.get("name"));
                //release_date
               trailerList.add(trailer);
               trailer=new Trailer();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return trailerList;
    }




}
