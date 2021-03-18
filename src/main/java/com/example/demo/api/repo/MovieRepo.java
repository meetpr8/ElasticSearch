package com.example.demo.api.repo;

import com.example.demo.api.model.movies;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/*This is the main repo that is extending ElasticsearchRepository and our Custom Repository. In the configuration file, we
 have enabled this repo and hence we can use it as a bean */
public interface MovieRepo extends ElasticsearchRepository<movies, String>,CustomRepo{

    public List<movies> findByTitle(String title);
}
