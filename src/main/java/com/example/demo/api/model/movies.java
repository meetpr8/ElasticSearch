package com.example.demo.api.model;

import org.apache.lucene.analysis.Analyzer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;
import java.util.List;

/*This is the movie class in where first we have mentioned the indexName below and then have defined fields
 with the same names as in the database.  All the other classes(IMDB, RottenTomatoes, ViewerRating) are used
 just as an object field for this class*/
@Document(indexName = "movies")
public class movies{
    @Id
    private String id;
    private String title;
    private int year;
    private List<String> cast;
    private String plot;
    private String fullPlot;
    private Date lastUpdated;
    private String type;
    private String poster;
    private List<String> directors;
    private List<String> writers;
    private IMDB imdb;

    private List<String> countries;
    private List<String> genres;

    private RottenTomatoes tomatoes;

    public movies() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String Plot) {
        this.plot = Plot;
    }

    public String getFullPlot() {
        return fullPlot;
    }

    public void setFullPlot(String fullPlot) {
        this.fullPlot = fullPlot;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public IMDB getImdb() {
        return imdb;
    }

    public void setImdb(IMDB imdb) {
        this.imdb = imdb;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public RottenTomatoes getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(RottenTomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }





}
