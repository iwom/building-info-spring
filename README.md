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

#FAQ:

### How to run this app for agile demo?
Simply navigate to `docker` dir and hit `$ docker-compose up`. Make sure that no other containers (associated with backend-builder project) are running. When creating containers is done, navigate to `localhost:8080` and test the API.
Spring logs from docker-container suite may tell you that backend-builder encountered an error while booting. Ignore this message, backend-builder becomes available shortly after mysql container does.

### Where are my javadocs?
Build the app locally then find generated javadoc in `/target/site/apidocs/com`
Run `index.html` with Web Browser (*important* not that easy to find & show via docker)

### Where is my .jar file?
When your app has been successfully run in docker type:
`$ docker container exec -it builder-backend bash`
Above starts bash _inside_ backend container. `app.jar` sits in home directory so type
`$ ls`
To exit bash inside a container: `$ exit`
