package com.example.android.movieapp.Model;

/**
 * Created by Mohamed Ramadan on 4/21/2016.
 */
public class Review {
    String content , url ,author;



    public Review() {


    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }
}
