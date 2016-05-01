package com.example.android.movieapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements MainFragment.Communicator {
    MainFragment mainFragment;
    DetailFragment detailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        mainFragment=(MainFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1);
        mainFragment.setCommunicator(this);
        //detailFragment=(DetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(this,SettingActivity.class));
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(Movie movie) {

    detailFragment=(DetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);

        if(detailFragment!=null && detailFragment.isVisible()){

             detailFragment.updateData(movie);

        }

        else{


                Intent intent=new Intent(this,MovieDetailActivity.class);
                intent.putExtra("movieClass",movie);
                startActivity(intent);
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */

}
