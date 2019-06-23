package com.example.manara.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manara.movies.View_Model.Database;
import com.example.manara.movies.View_Model.FavoriteVMFactory;
import com.example.manara.movies.View_Model.Type_Convertor;
import com.example.manara.movies.View_Model.ViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Trailer>>,Trailer_Adapter.ListItemClickListener {

    TextView Overviwe;
    TextView Titlemovie;
    TextView Vote;
    TextView Datemovie;
    ImageView Poster;
    List<Trailer> Trailerlist = new ArrayList<>();
    List<Reviews> reviewsList = new ArrayList<>();
    ReviewLoader reviewLoader;
    Context context = this;
    Favorite_Table favorite_table;
    int id;
    int id2;
    private static final String URI_APP = "vnd.youtube:";
    private static final String URI_WEB = "http://www.youtube.com/watch?v=";
    Button favorite;
    String posterPath, title, date, overview, vote;
    List<Trailer> trailerList;
    Database database;
    Intent intent;
    Favorite_Table currentFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        database = Database.getInstance(this);

        intent = getIntent();

        favorite = findViewById(R.id.favorite);
        setTitle("MovieDetails");

        Overviwe = findViewById(R.id.overview);
        Vote = findViewById(R.id.vote);
        Datemovie = findViewById(R.id.date);
        Poster = findViewById(R.id.poster);
        Titlemovie = findViewById(R.id.title);
        if (savedInstanceState == null && intent.hasExtra("id")) {
            Intent intentThatStartedThisActivity = getIntent();
            if (intentThatStartedThisActivity.hasExtra("poster") && intentThatStartedThisActivity.hasExtra("overview") && intentThatStartedThisActivity.hasExtra("vote") && intentThatStartedThisActivity.hasExtra("date") && intentThatStartedThisActivity.hasExtra("id")) {

                vote = intentThatStartedThisActivity.getStringExtra("vote");
                overview = intentThatStartedThisActivity.getStringExtra("overview");
                date = intentThatStartedThisActivity.getStringExtra("date");
                posterPath = intentThatStartedThisActivity.getStringExtra("poster");
                title = intentThatStartedThisActivity.getStringExtra("title");
                id = intentThatStartedThisActivity.getIntExtra("id", 0);
                id2 = intentThatStartedThisActivity.getIntExtra("id", 0);
                Vote.setText(vote);
                Overviwe.setText(overview);
                Datemovie.setText(date);
                Titlemovie.setText(title);
                Picasso.with(this)
                        .load(posterPath)
                        .into(Poster);
            }
            //noinspection deprecation
            getSupportLoaderManager().initLoader(2, null, this);
        } else {

            vote = savedInstanceState.getString("vote");
            overview = savedInstanceState.getString("overview");
            date = savedInstanceState.getString("date");
            posterPath = savedInstanceState.getString("poster");
            title = savedInstanceState.getString("title");
            id=Integer.valueOf(savedInstanceState.getString("id"));
            Vote.setText(vote);
            Overviwe.setText(overview);
            Datemovie.setText(date);
            Titlemovie.setText(title);
            Picasso.with(this)
                    .load(posterPath)
                    .into(Poster);
            trailerList=Type_Convertor.stringToMovie(savedInstanceState.getString("trailer"));
            reviewsList=Type_Convertor.stringToReview(savedInstanceState.getString("review"));
            //Toast.makeText(context, String.valueOf(trailerList.size()), Toast.LENGTH_SHORT).show();
            setupTheRecyclerViews();

            }


        ViewModel viewModel= ViewModelProviders.of(this, new FavoriteVMFactory(this.getApplication(), id)).get(ViewModel.class);
        viewModel.getTaskEntry().observe(this, new Observer<Favorite_Table>() {
            @Override
            public void onChanged(@Nullable Favorite_Table fav) {
                currentFav = fav;
                if (fav != null){
                    favorite.setText("remove from Favorite");
                }else {
                    favorite.setText("ADD To Favorite");
                }
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorite.getText()=="ADD To Favorite") {

                    favorite_table = new Favorite_Table(id,posterPath, title, date, vote, overview, Trailerlist, reviewsList);

                    APP_Excuter.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            database.taskDao().Addfavorite(favorite_table);
                        }
                    });
                    Toast mToast = Toast.makeText(MovieDetails.this, "Film Added to Favorites", Toast.LENGTH_SHORT);
                    mToast.show();
                    favorite.setText("Remove From Favorite");
                }
                else {
                    new DeleteFavAsyncTask(database.taskDao()).execute(currentFav);
                    favorite.setText("ADD To Favorite");
                    Toast mToast = Toast.makeText(MovieDetails.this, "Deleted from Favourites", Toast.LENGTH_SHORT);
                    mToast.show();}
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("vote",vote);
        outState.putString("overview",overview);
        outState.putString("date",date);
        outState.putString("poster",posterPath);
        outState.putString("title",title);
        outState.putString("id",String.valueOf(id));
        outState.putString("trailer",Type_Convertor.movieToString(trailerList));
        outState.putString("review",Type_Convertor.reviewToString(reviewsList));
    }

    private void setupTheRecyclerViews() {

        RecyclerView moviereviews=findViewById(R.id.reviews_list);
        Review_Adapter review_adapter =new Review_Adapter(reviewsList,context);
        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(this);
        reviewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        moviereviews.setLayoutManager(reviewLayoutManager);
        moviereviews.setHasFixedSize(true);
        moviereviews.setAdapter(review_adapter);
        ///////////////////////////////////////

        RecyclerView movieTrailer=findViewById(R.id.trailer);
        Trailer_Adapter trailer_adapter =new Trailer_Adapter(Trailerlist,context,this);
        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(this);
        trailerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieTrailer.setLayoutManager(trailerLayoutManager);
        movieTrailer.setHasFixedSize(true);
        movieTrailer.setAdapter(trailer_adapter);

    }

    @NonNull
    @Override
    public Loader<List<Trailer>> onCreateLoader(int i, @Nullable Bundle bundle) {

        reviewLoader=new ReviewLoader(MovieDetails.this,id2);
        reviewLoader.forceLoad();
        return new TrailerLoader(MovieDetails.this,id);
    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<Trailer>> loader, List<Trailer> o) {
        reviewsList= reviewLoader.reviewsList1;

        Trailerlist=o;
        setupTheRecyclerViews();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Trailer>> loader) {

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    String videoKey=Trailerlist.get(clickedItemIndex).getKey();

        Intent youTubeApp = new Intent(Intent.ACTION_VIEW, Uri.parse(URI_APP + videoKey));
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(URI_WEB + videoKey));

        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
        try {
            context.startActivity(youTubeApp);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(browser);
        }

    }

    private static class DeleteFavAsyncTask extends AsyncTask<Favorite_Table, Void, Void> {
        private TaskDao mAsyncTaskDao;

        DeleteFavAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite_Table... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }
    }


}
