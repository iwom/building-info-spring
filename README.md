# building-info-spring

# How to:

## Run:
  - clone this repo
  - go to directory with docker-compose*.yml
  - docker-compose up
  - visit localhost:8080/enter-your-random-text-here

## Develop:
  - clone this repo
  - go to directory with docker-compose*.yml
  - docker-compose -f docker-compose-mysql.yml up
  - use provided mysql container with spring boot `docker` profile for local development
  - `default` profile also available with H2 in-memory database
