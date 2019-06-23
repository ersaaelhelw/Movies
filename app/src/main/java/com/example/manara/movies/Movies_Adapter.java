package com.example.manara.movies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Movies_Adapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>   {
    Context context;
    static  int position;
    public List<Movie> movieList=new ArrayList<>();

    final private ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public Movies_Adapter(List<Movie> movieList, Context context, ListItemClickListener listener) {
        this.movieList=movieList;
        this.context=context;
        mOnClickListener = listener;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.rv_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new Movies_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(  RecyclerView.ViewHolder holder,   int i) {
        ImageView imageView= holder.itemView.findViewById(R.id.image_movie);
          Picasso.with(context)
                .load(movieList.get(i).getPoster())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public ViewHolder(View view) {

                super(view);
            itemView.setOnClickListener(this);

            }

        @Override
        public void onClick(View v) {


            int pos = getAdapterPosition();


            mOnClickListener.onListItemClick(pos);

        }
    }
}
