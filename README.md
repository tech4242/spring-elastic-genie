[![Build Status](https://travis-ci.org/tech4242/spring-elastic-genie.svg?branch=master)](https://travis-ci.org/tech4242/spring-elastic-genie)

# spring-elastic-genie

## Intro

This project will showcase how to use Elasticsearch and Kibana with Spring Boot. You can add movies fetched from OMDB to your PostgreSQL database through a simple CRUD REST interface, which will in turn be indexed by Elasticsearch. Once indexed, you can visualize your data with Kibana.

## Local build with gradle

```bash
./gradlew build && java -jar build/libs/spring-elastic-genie-0.0.1.jar
```

## Docker build

```bash
./gradlew build
docker-compose build
docker-compose up
```

## Using the API

Here is how you can use the app. This POST request will send a search query to OMDB's API and save the movies in your local PostgreSQL DB, which can in turn be indexed by Elasticsearch.

```bash
curl -X POST \
  http://localhost:8080/movie/add \
  -H 'Content-Type: application/json' \
  -d '{
    "search": "Rush Hour 3"
}'
```