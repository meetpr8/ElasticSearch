package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "movies")
@Getter @Setter
public class RottenTomatoes {
    private Date lastUpdated;
    private ViewerRating viewer;

}
