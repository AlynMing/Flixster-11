package com.ldong.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldong.flixster.R;
import com.ldong.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;
    public static final String ADAPTER_TAG = "MovieAdapter";

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(ADAPTER_TAG, "onCreateViewHolder");
        //Create a new view
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(ADAPTER_TAG,"onBindViewHolder" + position);
        //Get the movie at the passed-in position
        Movie movie = movies.get(position);
        //Bind the movie data into the VH
        holder.bind(movie);

    }

    //Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //ViewHolder is a representation of the rows in the recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mvTitle;
        TextView mvOverview;
        ImageView mvPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mvTitle = itemView.findViewById(R.id.mvTitle);
            mvOverview = itemView.findViewById(R.id.mvOverview);
            mvPoster = itemView.findViewById(R.id.mvPoster);
        }


        public void bind(Movie movie) {
            mvTitle.setText(movie.getTitle());
            mvOverview.setText(movie.getOverview());
            String imageUrl;

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            } else{
                imageUrl = movie.getPosterPath(); //default
            }

            Glide.with(context).load(imageUrl).into(mvPoster);
        }
    }
}
