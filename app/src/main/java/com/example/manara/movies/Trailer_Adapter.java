package com.example.manara.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Trailer_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    final private Trailer_Adapter.ListItemClickListener mOnClickListener;
    public List<Trailer> trailerList =new ArrayList<>();

    Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public Trailer_Adapter(List<Trailer> trailerList, Context context,  ListItemClickListener listener) {
        this.trailerList=trailerList;
        this.context=context;
        mOnClickListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trailer_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new Trailer_Adapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Button button= viewHolder.itemView.findViewById(R.id.trailer);
        button.setText(trailerList.get(i).getName());




    }

    @Override
    public int getItemCount() {
        return trailerList.size();
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
