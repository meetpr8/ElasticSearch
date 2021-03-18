package com.example.demo.api.repo;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.support.AggregationPath;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomRepoImpl implements CustomRepo{
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /*To get top 10 directors in terms of their highest IMDB rated movie, first I have done the term aggregation
     directors field and then have defined a subAggregation in it which is a Metrics Aggregation getting maximum of
     IMDB ratings of all the particular director's movies. As I just wanted to get the result of aggregation, I have
     done .size(0) to get 0 hits search hits.*/
    @Override
    public SearchResponse getTopImdbDirectors() {
        SearchRequest searchRequest = new SearchRequest("movies");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0).
            aggregation(AggregationBuilders.terms("top10Directors").field("directors").
                order(BucketOrder.aggregation("maxRating",false)).
                    subAggregation(AggregationBuilders.max("maxRating").field("imdb.rating")).
                        size(10));
        searchRequest.source(searchSourceBuilder);
        try{
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

//        return elasticsearchOperations.search(query, movies.class);
        return null;
    }

}
