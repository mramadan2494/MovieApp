package com.example.android.movieapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohamed Ramadan on 5/1/2016.
 */
public class MovieHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION=3;
    private final static String DATABASE_NAME="favoriteDatabase" ;

    public  MovieHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);


    }






    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE=" CREATE TABLE " +DataBaseContract.TABLE_NAME+" ( "+
                DataBaseContract.MOVIE_ID+" INTEGER PRIMARY KEY , "+
                DataBaseContract.MOVIE_VOTE+" INTEGER , "+
                DataBaseContract.MOVIE_NAME+" VARCHAR(100) , " +
                DataBaseContract.MOVIE_POSTER+" VARCHAR(100) , "+
                DataBaseContract.MOVIE_OVERVIEW+" VARCHAR(300) , " +
                DataBaseContract.MOVIE_Date+" VARCHAR(30)); ";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE="DROP TABLE IF EXISTS "+DataBaseContract.TABLE_NAME;
        db.execSQL(DROP_TABLE);

    }

}
