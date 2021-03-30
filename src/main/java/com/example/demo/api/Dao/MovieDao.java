package com.example.demo.api.Dao;

import com.example.demo.api.model.movies;
import com.example.demo.api.repo.MovieRepo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*This is the Dao module, where all the Database related implementation is written of all the functions that are there
in the service
*/
@Component
public class MovieDao {
    /*
     elasticSearchOperations and restHighLevelClient beans are coming from our configuration file which has
     extended AbstractElasticsearchConfiguration that includes this two beans.*/
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    // This bean is coming from Movie Repository
    @Autowired
    MovieRepo movieRepo;

    /*Implementation of getting movie from title by defining simple Criteria and using the elasticSearchOperation
     and its search function to execute it.*/
    public SearchHits<movies> getMoviesByTitle(String title)
    {
        Criteria criteria = new Criteria("title").is(title);
        Query query = new CriteriaQuery(criteria);
        return elasticsearchOperations.search(query, movies.class);
    }
    /*Getting movies of an actor in some sorted manner. Again have defined the Criteria and have used addSort
     method of CriteriaQuery to define the sorting which is in first in descending order of tomatoes rating and
     then in case of tie, ascending order of year of release of the movie*/
    public SearchHits<movies> getMoviesOfActor(String actor)
    {
        Criteria criteria = new Criteria("cast").is(actor);
        Query query = new CriteriaQuery(criteria).
                addSort(Sort.by("tomatoes.viewer.rating").descending().
                        and(Sort.by("year").ascending()));
        return elasticsearchOperations.search(query, movies.class);
    }
    /*Here I was trying to use restHighLevelClient and SearchSourceBuilder to execute this query because I wanted
    to return some specified fields only as the answer and I was not able to do that using CriteriaQuery or
     NativeSearchQueryBuilder*/
    public SearchResponse getMoviesOfDirector(String director)
    {
        String[] include = new String[]{"title", "directors", "year"};
        String[] exclude = new String[0];
        SearchRequest searchRequest = new SearchRequest("movies");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(3).
                query(QueryBuilders.matchQuery("directors", director)).
                fetchSource(include, exclude);
        searchRequest.source(searchSourceBuilder);
        try{
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        return null;
    }
    /*Here I have tried to implement aggregation based queries. Now in this one, I have created a CustomRepo
    which has just one method in it, getTopImdbDirectors(), whose implementation I have defined in
    CustomRepoImpl. During the execution, it will search for the class with name "custom repository name +
    Impl suffix" for implementation details of all the function defined in CustomRepo interface*/
    public SearchResponse getTop10ImdbDirectors()
    {
/*        Query query = new NativeSearchQueryBuilder().
                addAggregation(AggregationBuilders.terms("top10Directors").field("directors").
                subAggregation(AggregationBuilders.max("maxRating").field("tomatoes.critic.rating"))).
                withQuery(QueryBuilders.matchQuery("Directors", "Christopher Nolan")).build();*/
        return movieRepo.getTopImdbDirectors();
    }
    /*It's implementation of phrase search using NativeSearchQueryBuilder as well as restHighLevelClient
    (commented part) */
    public SearchHits<movies> getPhrase(String text)
    {
        /*SearchRequest searchRequest = new SearchRequest("movies");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().
                query(QueryBuilders.matchPhraseQuery("fullplot", text));
        searchRequest.source(searchSourceBuilder);
        try{
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        return null;*/
        Query query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchPhraseQuery("plot", text)).build();
        return elasticsearchOperations.search(query, movies.class);
    }

    /*Saving a document using save method of CrudRepository which ElasticsearchRepository implements*/
    public void add(movies movie)
    {
        movieRepo.save(movie);
    }


    public SearchHits<movies> autoSuggestInPlot(String text) {
        String[] include = new String[]{"title", "directors", "plot"};
        String[] exclude = new String[0];
        SourceFilter sourceFilter = new FetchSourceFilter(include, exclude);
        Query query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchQuery("plot", text)).
                withSourceFilter(sourceFilter).build();
        return elasticsearchOperations.search(query, movies.class);
    }
}
