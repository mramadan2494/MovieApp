package com.example.android.movieapp.DataBase;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Mohamed Ramadan on 5/1/2016.
 */
public class MovieProvider extends ContentProvider {

    MovieHelper movieHelper ;

    static final UriMatcher matcher;
    static{
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DataBaseContract.AUTHORITY, "movies", DataBaseContract.MOVIES);
        matcher.addURI(DataBaseContract.AUTHORITY, "movies/#", DataBaseContract.MOVIE_WITH_ID);
    }


    @Override
    public boolean onCreate() {

        movieHelper=new MovieHelper(getContext());

        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = matcher.match(uri);

        switch (match){
        case DataBaseContract.MOVIES:
        return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + DataBaseContract.AUTHORITY + "/" + DataBaseContract.PATH;

        case DataBaseContract.MOVIE_WITH_ID:
        return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + DataBaseContract.AUTHORITY + "/" + DataBaseContract.PATH;

        default:
        throw new UnsupportedOperationException("Unknown uri ");

    }

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = movieHelper.getWritableDatabase();
        Cursor cursor;
        switch (matcher.match(uri)) {
            case DataBaseContract.MOVIES:
                cursor = db.query(DataBaseContract.TABLE_NAME, null, null, null, null, null, null);
                break;

            case DataBaseContract.MOVIE_WITH_ID: {
                String id = uri.getPathSegments().get(1);
                cursor = db.query(DataBaseContract.TABLE_NAME, null, DataBaseContract.MOVIE_ID + " = " + id, null, null, null, null);

                break;
            }
            default:
               return null;



        }
        return cursor;
    }



    @Override
    public Uri insert (Uri uri , ContentValues values) {

          SQLiteDatabase db=movieHelper.getWritableDatabase();
        long id ;
        id=db.insert(DataBaseContract.TABLE_NAME , null , values);

        if(id>0){

            Uri _uri = ContentUris.withAppendedId(DataBaseContract.URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        return null;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = movieHelper.getWritableDatabase();
         int affectedrows = 0;

        switch (matcher.match(uri)) {
            case DataBaseContract.MOVIES:
                affectedrows = db.delete(DataBaseContract.TABLE_NAME, null, null);
                break;

            case DataBaseContract.MOVIE_WITH_ID:{
                String id = uri.getPathSegments().get(1);
                affectedrows = db.delete(DataBaseContract.TABLE_NAME, DataBaseContract.MOVIE_ID + " = " + id , null);
                break;}

            default:
               return 0;


        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affectedrows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}


