package com.example.android.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mohamed Ramadan on 24/03/2016.
 */
public class MovieAdapter extends BaseAdapter  {

    List<String> movies;
    Context context;



    public MovieAdapter (Context context ){
        this.context=context;



    }


    public void setMovies(List movies){

        this.movies=movies;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
        public Object getItem(int position) {


        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {


        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ImageView movieImage=null;
        if(row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, parent, false);
            movieImage=(ImageView)row.findViewById(R.id.image_movie);
            row.setTag(movieImage);

        }
        else{
            movieImage=(ImageView)row.getTag();

        }


        Picasso.with(context).load("https://image.tmdb.org/t/p/w185"+movies.get(position)).into(movieImage);
        //System.out.println("https://image.tmdb.org/t/p/original"+movies.get(position));

        return row;
    }
}
