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
docker-compose build
docker-compose up
```