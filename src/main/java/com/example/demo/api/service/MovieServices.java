package com.example.demo.api.service;

import com.example.demo.api.Dao.MovieDao;
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
import org.springframework.stereotype.Service;

import java.io.IOException;

/*This is the service module, where all the implementation is written of all the functions that are there
in the controller. Have kept the service and Dao separate so that all the database related implementation is not in the
service module
*/
@Service
public class MovieServices {
    @Autowired
    MovieDao movieDao;
    public SearchHits<movies> getMoviesByTitle(String title)
    {
        return movieDao.getMoviesByTitle(title);
    }
    public SearchHits<movies> getMoviesOfActor(String actor)
    {
        return movieDao.getMoviesOfActor(actor);
    }
    public SearchResponse getMoviesOfDirector(String director)
    {
        return movieDao.getMoviesOfDirector(director);
    }
    public SearchResponse getTop10ImdbDirectors()
    {
        return movieDao.getTop10ImdbDirectors();
    }
    public SearchHits<movies> getPhrase(String text)
    {
        return movieDao.getPhrase(text);
    }
    public void add(movies movie)
    {
        movieDao.add(movie);
    }


    public SearchHits<movies> autoSuggestInPlot(String text) {
        return movieDao.autoSuggestInPlot(text);
    }
}
