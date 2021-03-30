package com.example.demo.api.controller;

import com.example.demo.api.model.movies;
import com.example.demo.api.service.MovieServices;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/movies")
// It's a controller with all the REST endpoints exposed for querying.
public class MovieController {
    @Autowired
    MovieServices movieServices;
    @GetMapping(value = "/{title}")
    private SearchHits<movies> moviesByTitle(@PathVariable(value = "title") String title)
    {
        return movieServices.getMoviesByTitle(title);
    }
    // Here, it will return movies of a given actor in descending order of their Rotten Tomatoes Ratings.
    @GetMapping(value = "/moviesOf/actor/{actor}")
    private SearchHits<movies> moviesOfActor(@PathVariable(value = "actor") String actor)
    {
        return movieServices.getMoviesOfActor(actor);
    }

    @GetMapping(value = "/moviesOf/director/{director}")
    private SearchResponse moviesOfDirector(@PathVariable(value = "director") String director)
    {
        return movieServices.getMoviesOfDirector(director);
    }

    //Here, it's returning top 10 directors in terms of their highest IMDB rated movie.
    @GetMapping(value = "/top10ImdbDirectors")
    private SearchResponse top10ImdbDirectors()
    {
        return movieServices.getTop10ImdbDirectors();
    }

    //This is just an example of to search a particular phrase in the fullplot of the movie
    @GetMapping(value = "/searchPlot/{text}")
    private SearchHits<movies> phraseSearch(@PathVariable(value = "text") String text)
    {
        return movieServices.getPhrase(text);
    }

    // Just adding a new movie.
    @PutMapping(value = "/add")
    private void addMovie(@RequestBody movies movie)
    {
        movieServices.add(movie);
    }

    //Auto-Suggest Example
    @GetMapping(value = "/autoSuggest/{text}")
    private SearchHits<movies> autoSuggestInPlot(@PathVariable String text)
    {
        return movieServices.autoSuggestInPlot(text);
    }


}
