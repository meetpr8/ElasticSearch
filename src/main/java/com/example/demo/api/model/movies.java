package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.Analyzer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;
import java.util.List;

/*This is the movie class in where first we have mentioned the indexName below and then have defined fields
 with the same names as in the database.  All the other classes(IMDB, RottenTomatoes, ViewerRating) are used
 just as an object field for this class*/
@Document(indexName = "movies")
@Setting(settingPath = "templates/autoCompleteAnalyzer.json")
@Getter @Setter
public class movies{
    @Id
    private String id;
    private String title;
    private int year;
    private List<String> cast;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
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

}
