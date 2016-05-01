package com.example.android.movieapp.Model;

/**
 * Created by Mohamed Ramadan on 4/21/2016.
 */
public class Trailer  {

        String key , name ,site ;

    public Trailer() {

    }


    public void setName(String name) {
        this.name = name;
    }


    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
