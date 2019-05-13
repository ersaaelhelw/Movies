package com.example.manara.movies.View_Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.manara.movies.Movie;
import com.example.manara.movies.Movies_Adapter;
import com.example.manara.movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Favorate_Adapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>   {

    public List<String> posters=new ArrayList<>();

    Context context;


     private  final ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);

    }

    public Favorate_Adapter(List<String> posters, Context context,ListItemClickListener listener) {
        this.posters=posters;
        this.context=context;
        mOnClickListener = listener;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.rv_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new Favorate_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(  RecyclerView.ViewHolder holder,   int i) {
        ImageView imageView= holder.itemView.findViewById(R.id.image_movie);
        Picasso.with(context)
                .load(posters.get(i))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return posters.size();
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
