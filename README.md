# ElasticSearch

It's an implementation of queries in Elasticsearch using Spring Data Elasticsearch. 

## Usage

To see the codes, go into the ElasticSearch/src/main/java/com/example/demo/ directory where there are two folders, api and config.
 
Config: It has code for the configuration of ElasticSearch Client and some other configuration.   

Api: Here is where all the implementation of queries. There are 5 directories in it:

    Controller: It has controllers with all the REST endpoints exposed for querying.
    Model: It has classes/models which fields are mapped with the fields of corresponding 
           index in elasticsearch.
    Service: This is the service module where all the logic, which does not include 
             the access of database, is implemented.
    Dao: This is the module where all the database related logic is implemented.
    Repo: This module is having repositories and their implementation incase of custom 
          repository

