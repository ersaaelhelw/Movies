package com.example.manara.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Review_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    public List<Reviews> reviewsList=new ArrayList<>();
    Context context;


    public Review_Adapter(List<Reviews> reviewsList, Context context) {
        this.reviewsList=reviewsList;
        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.review_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new Review_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView author=viewHolder.itemView.findViewById(R.id.author);
        author.setText(reviewsList.get(i).getAuthor());
        TextView content=viewHolder.itemView.findViewById(R.id.content);
        content.setText(reviewsList.get(i).getContent());



    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}
