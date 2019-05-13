package com.example.manara.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.manara.movies.View_Model.Favorate_Adapter;
import com.example.manara.movies.View_Model.View_Model_Movies;

import java.util.ArrayList;
import java.util.List;

public class Favorite_Activity extends AppCompatActivity implements  Favorate_Adapter.ListItemClickListener {
    RecyclerView FavoratePoster ;
    Context context=this;
    List<String>posterpath=new ArrayList<>();
    List<Favorite_Table> movieFavouratesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_);
    }

    @Override
    protected void onResume() {
        super.onResume();

        View_Model_Movies viewModel= ViewModelProviders.of(this).get(View_Model_Movies.class);
        viewModel.getFavourate().observe(this, new Observer<List<Favorite_Table>>() {
            @Override
            public void onChanged(@Nullable List<Favorite_Table> favorite_tables) {
                if (favorite_tables != null){
                    movieFavouratesArrayList=favorite_tables;
                    // Toast.makeText(context, String.valueOf(favorite_tables.get(0).name) , Toast.LENGTH_SHORT).show();
                    posterpath.clear();
                    for(int i=0;i<movieFavouratesArrayList.size();i++) {
                        posterpath.add(movieFavouratesArrayList.get(i).poster);
                    }
                    FavoratePoster=findViewById(R.id.favorite_poster);
                    Favorate_Adapter favorate_adapter=new Favorate_Adapter(posterpath,context,Favorite_Activity.this);
                    FavoratePoster.setLayoutManager(new GridLayoutManager(context, 2,GridLayoutManager.VERTICAL,false));
                    FavoratePoster.setAdapter(favorate_adapter);

                }
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Favorite_Table favorite = movieFavouratesArrayList.get(clickedItemIndex);
        Intent intent=new Intent(Favorite_Activity.this,MovieDetails.class);
        intent.putExtra("id",favorite.id);
        intent.putExtra("poster",favorite.poster);
        intent.putExtra("vote",favorite.vote);
        intent.putExtra("overview",favorite.overview);
        intent.putExtra("date",favorite.date);
        intent.putExtra("title",favorite.name);
        startActivity(intent);

    }


}
