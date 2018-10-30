# building-info-spring

# How to:

## Run:
  - clone this repo
  - go to directory with docker-compose*.yml
  - docker-compose up
  - default compose .yml starts app with `docker` Spring profile
  - visit localhost:8080/enter-your-random-text-here

## Develop:
  - clone this repo
  - go to directory with docker-compose*.yml
  - docker-compose -f docker-compose-mysql.yml up
  - use provided mysql container with Spring Boot `mysql` profile for local development
  - `default` profile also available with H2 in-memory database

## Test:
  - tests executed in CI build
  - see .travis.yml to configure mvn behaviour
  - Spring profile is not set and falls back to `default` in-memory H2
