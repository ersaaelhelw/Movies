package com.example.manara.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.manara.movies.View_Model.Favorate_Adapter;
import com.example.manara.movies.View_Model.ViewModel;
import com.example.manara.movies.View_Model.View_Model_Movies;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Movies_Adapter.ListItemClickListener {
    Context context=this;
    List<Movie> movie=new ArrayList<>();
    RecyclerView MoviePoster ;
    public static int index = -1;
    public static int top = -1;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager mLayoutManager;

    String t="popular";
    SetData setData=new SetData();
    SetData setData1=new SetData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        setTitle("Pop Movies");
        if (savedInstanceState!=null)
        {
            int x= (int) savedInstanceState.get("index");

            if (savedInstanceState.get("query").equals("popular"))
            {
                new SetData().execute();


            }
            else
            {
                t="top_rated";
                new SetData().execute();


            }
        }
        else {
            new SetData().execute();

        }}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class SetData extends AsyncTask<Void,Void,Void> {
        URL url;

        {
            try {
                url = new URL("http://api.themoviedb.org/3/movie/"+t+"?api_key=f236e35beb4f4914e7e13dc23b4b1fc4");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                GetData getData=new GetData(url);
                movie= getData.movie_list();



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;




        }
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            MoviePoster=findViewById(R.id.movie_poster);
            Movies_Adapter movies_adapter=new Movies_Adapter(movie,context, MainActivity.this);
            MoviePoster.setLayoutManager(gridLayoutManager);

            MoviePoster.setAdapter(movies_adapter);

//
//
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query",t);
        outState.putInt("index",index);


    }

    @Override
    public void onPause()
    {
        super.onPause();
        //read current recyclerview position
        index = gridLayoutManager.findFirstVisibleItemPosition();
        View v = MoviePoster.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - MoviePoster.getPaddingTop());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //set recyclerview position
        if(index != -1)
        {
            gridLayoutManager.scrollToPositionWithOffset( index, top);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular: {
                t="popular";

                 new SetData().execute();
                return true;
            }
            case R.id.top_rated: {

                t="top_rated";
                new SetData().execute();
                return true;

            }
// User chose the "Favorite" action, mark the current item
                // as a favorite...

            case R.id.favorite:
            {

                Intent intent=new Intent(MainActivity.this,Favorite_Activity.class);
                startActivity(intent);


            }
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }





    @Override
    public void onListItemClick(int clickedItemIndex) {

String poster;
String overviwe;
String title;
Number vote;
String date;
int id;
        Context context = MainActivity.this;
        Class destinationActivity = MovieDetails.class;
        Intent startChildActivityIntent = new Intent(context, destinationActivity);
        poster=  movie.get(clickedItemIndex).getPoster();
        title=movie.get(clickedItemIndex).getName();
        vote=movie.get(clickedItemIndex).getVote();
        String xvote=   vote.toString();
        date=movie.get(clickedItemIndex).getDate();
        overviwe=  movie.get(clickedItemIndex).getReview();

        id=movie.get(clickedItemIndex).getId();

        startChildActivityIntent.putExtra("poster",poster);
        startChildActivityIntent.putExtra("vote",xvote);
        startChildActivityIntent.putExtra("overview",overviwe);
        startChildActivityIntent.putExtra("date",date);
        startChildActivityIntent.putExtra("title",title);
        startChildActivityIntent.putExtra("id",id);

        startActivity(startChildActivityIntent);

    }





}