[![Build Status](https://travis-ci.org/tech4242/spring-elastic-genie.svg?branch=master)](https://travis-ci.org/tech4242/spring-elastic-genie)

# spring-elastic-genie

## Intro

This project will showcase how to use Elasticsearch and Kibana with Spring Boot. You can add movies fetched from OMDB to your PostgreSQL database through a simple CRUD REST interface, which will in turn be indexed by Elasticsearch. Once indexed, you can visualize your data with Kibana.

## Config

In order to configure this app you should look at:

- `core.env.dist` and create a `core.env` file with those variables (used by docker-compose etc.)
- `application.properties` contains all the bindings for the defined ENV variables for the DB and Elasticsearch.
- `/logstash/config-dir/` contains all the standard configs from Logstash with a custom `logstash.conf`, which has the DB credentials etc. and `drivers/` contains the pgjdbc driver needed to connect to PostgreSQL

*Logstash Disclaimer: currently Logstash is only configured to get data from PostgreSQL with a simple SQL query but it does not have filters for duplicates etc. There are numerous Logstash tutorials you can follow to filter your data.*

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