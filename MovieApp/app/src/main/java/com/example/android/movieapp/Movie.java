package com.example.android.movieapp;


import java.io.Serializable;

public class Movie implements Serializable{
    String imagePath;
    String title ,overview , release_date ;
    int id , vote_average;


    public Movie(){



    }
    public void setID(int id){

        this.id=id;


    }

    public void setImagepath(String imgPath){

        this.imagePath=imgPath;


    }

    public void setTitle(String title){

        this.title=title;


    }

    public void setOverview(String overview){

        this.overview=overview;


    }
    public void setDate(String date){

        this.release_date=date;


    }

    public void setRate(int vote_average){

        this.vote_average=vote_average;


    }

    public String getImagePath(){

        return imagePath;
    }
    public int  getID(){

        return id;


    }

    public String getTitle(){

        return title;


    }

    public String getOverview(){

        return overview;


    }
    public String getDate(){

        return release_date;


    }

    public int getRate(){

        return vote_average;


    }


}
