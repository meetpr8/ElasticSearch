package com.example.demo.api.repo;

import org.elasticsearch.action.search.SearchResponse;

/*This is the Custom Repository to which, MovieRepo has extended. Implementation of it's functions are in the
    CustomRepoImpl class */
public interface CustomRepo {
    public SearchResponse getTopImdbDirectors();
}
