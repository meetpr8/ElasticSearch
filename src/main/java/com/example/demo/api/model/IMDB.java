package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "movies")
@Getter @Setter
public class IMDB {
    private int id;
    private int votes;
    private double rating;

}
