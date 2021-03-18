package com.example.demo.config;

import com.example.demo.api.repo.MovieRepo;
import org.elasticsearch.client.RestHighLevelClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/*Firstly, we have enabled the ElasticSearchRepository so we can use it as a bean that we have in MovieService class*/
@Configuration
@EnableElasticsearchRepositories(basePackageClasses = MovieRepo.class)
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

    /*This function is there in the AbstractElasticsearchConfiguration that we have overridden for our custom configuration.
     Using the ClientConfiguration, we are defining he port where elastic search is running */
    @Override
    public RestHighLevelClient elasticsearchClient()
    {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder().
                connectedTo("localhost:9200").build();
        return RestClients.create(clientConfiguration).rest();
    }
    /*I needed to customize this Jackson2ObjectMapperBuilderCustomizer because I was getting "keyAsNumber" whenever I was
    using aggregation on string fields as it was expecting key to be a number*/
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer changeKeyAsNumber() {
        return new Jackson2ObjectMapperBuilderCustomizer() {

            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.mixIn(ParsedStringTerms.ParsedBucket.class, MixIn.class);
            }
        };
    }
}
abstract class MixIn {
    @JsonIgnore
    abstract public Number getKeyAsNumber();
}
