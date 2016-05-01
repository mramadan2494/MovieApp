package com.example.android.movieapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class SettingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
         //setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.fragment_frame, new SettingFragment()).commit();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home){
            // NavUtils.navigateUpFromSameTask(this);
            NavUtils.getParentActivityIntent(this);
            //startActivity(new Intent(this,MainActivity.class));

        }
        return super.onOptionsItemSelected(item);

    }


}
