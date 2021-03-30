package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "movies")
@Getter @Setter
public class ViewerRating {
    private double rating;
    private int numReviews;

}
