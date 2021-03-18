package com.example.demo.api.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "movies")
public class IMDB {
    private int id;
    private int votes;
    private double rating;

    public IMDB() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
