package com.example.android.movieapp.DataBase;

import android.net.Uri;

/**
 * Created by Mohamed Ramadan on 5/1/2016.
 */
public class DataBaseContract {


    public static final String AUTHORITY = "com.example.android.movie";
    public static final String PATH="movies";
    public static final String URL = "content://" + AUTHORITY + "/"+PATH;
    public static final Uri URI = Uri.parse(URL);

//    static final String _ID = "_id";

    public static final int MOVIES = 1;
    public static final int MOVIE_WITH_ID  = 2;



    public final static String TABLE_NAME="favoriteTable" ;
    public final static String MOVIE_NAME="name" ;
    public final static String MOVIE_ID="_id" ;
    public final static String MOVIE_POSTER="poster" ;
    public final static String MOVIE_OVERVIEW="overview" ;
    public final static String MOVIE_Date="date" ;
    public final static String MOVIE_VOTE="vote" ;






}
