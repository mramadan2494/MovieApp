package com.example.android.movieapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.movieapp.DataBase.DataBaseContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mohamed Ramadan on 24/03/2016.
 */
public  class MainFragment extends Fragment  {
    MovieAdapter adapter ;
    GridView gridView;
    ArrayList<Movie> movies;
    Communicator com;
    public MainFragment() {
     movies=new ArrayList<Movie>();
    }

   public void setCommunicator(Communicator c){

      com=c;

   }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
         gridView = (GridView) rootView.findViewById(R.id.gridView);



       adapter = new MovieAdapter(getActivity());





      setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        updateData();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie;
                movie=movies.get(position);


                com.respond(movie);

            }
        });
    }


    public void updateData(){
        movies=new ArrayList<>();

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String showType;

        showType=sharedPreferences.getString(getString(R.string.showType_key),getString(R.string.showType_default));


        if(showType.equals("favorite")){
            ArrayList<String> posters=new ArrayList<String>();

            Cursor cursor= getActivity().getContentResolver().query(DataBaseContract.URI,null,null,null,null);



            while(cursor.moveToNext()){
                Movie movie=new Movie();
                movie.setID(cursor.getInt(cursor.getColumnIndex(DataBaseContract.MOVIE_ID)));
                movie.setRate(cursor.getInt(cursor.getColumnIndex(DataBaseContract.MOVIE_VOTE)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseContract.MOVIE_NAME)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(DataBaseContract.MOVIE_OVERVIEW)));
                movie.setDate(cursor.getString(cursor.getColumnIndex(DataBaseContract.MOVIE_Date)));
                movie.setImagepath(cursor.getString(cursor.getColumnIndex(DataBaseContract.MOVIE_POSTER)));
                posters.add(movie.getImagePath());
                movies.add(movie);

            }
            adapter.setMovies(posters);
            gridView.setAdapter(adapter);

        }

        else {

            FetchMovies fetch = new FetchMovies();
            fetch.execute(showType);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.mainfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
           if(id==R.id.refresh){

               updateData();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchMovies extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> data) {
           if(data!=null) {
           //    List temp = Arrays.asList(data);
               // Toast.makeText(getActivity(),temp.get(0).toString(),Toast.LENGTH_LONG).show();

               adapter.setMovies(data);
               gridView.setAdapter(adapter);

           }



        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList doInBackground(String... params) {


            String type;
            type=params[0];
            BufferedReader reader = null;
            String dataJson=null;
            try {

                HttpURLConnection urlConnection ;

                String baseUrl = "http://api.themoviedb.org/3/movie/";
                baseUrl+=type+"?";

                String api_key = "";
                Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
                builder.appendQueryParameter("api_key",api_key);

                URL url = new URL(builder.build().toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.connect(); ////////////////////

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();


                if (inputStream == null) {

                    return null ;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {

                    return null ;
                }
                dataJson = buffer.toString();


                try {
                    return moviesImages(dataJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
               System.out.println("error"+e.toString());
                e.printStackTrace();
            }

            return null;
        }


public ArrayList<String> moviesImages(String dataJson) throws JSONException {

    //  Movie  movies=new Movie();

    // Movie mov = new Movie();
    //movies.clear();

    JSONObject  jsonObject=new JSONObject(dataJson);

    JSONArray jsonArray=jsonObject.getJSONArray("results");


    ArrayList <String> temp=new ArrayList<>();

    for(int i=0;i<jsonArray.length();i++){

        Movie mov = new Movie();
        temp.add(jsonArray.getJSONObject(i).getString("poster_path"));

        mov.setID(jsonArray.getJSONObject(i).getInt("id"));
        mov.setOverview(jsonArray.getJSONObject(i).getString("overview"));
        mov.setDate(jsonArray.getJSONObject(i).getString("release_date"));
        mov.setTitle(jsonArray.getJSONObject(i).getString("title"));
        mov.setImagepath(jsonArray.getJSONObject(i).getString("poster_path"));
        mov.setRate(jsonArray.getJSONObject(i).getInt("vote_average"));
        movies.add(mov);



    }

    return temp;
}


    }

public interface Communicator
{

    public void respond(Movie movie);
}


}