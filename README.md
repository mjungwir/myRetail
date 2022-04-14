# myRetail
This repository serves as an example project for creating a REST API to serve simple product name and pricing data as laid out by requirements.

I chose to make a Groovy Spring boot app using Spring initializer to get started. I used MongoDB as a data store for the pricing data.

## Prerequisites
This uses the gradle wrapper, jdk17, and a docker compose file. I used Docker Desktop, but you'll need some container runtime that can run a docker-compose file.

### Note
This was built on a windows machine using Git Bash. It was tested on a Mac and all that had to be done was `chmod` permissions on `gradlew` and `startDocker.sh`

## Running Docker
The application expects docker to be running MongoDB locally on port `27017`. It might be more convenient to build the container lifecycle into the build/test workflow, but this is not yet completed.

You can run docker with `docker-compose up` or whichever container interface you prefer. If you are using `docker-compose` you can also leverage the script to make cleaning up from the previous run easy. `./startDocker.sh`

MongoDB UI is available at `http://localhost:8081`

## Building the App
```./gradlew build```

## Running the App
```./gradlew bootrun```

## Testing the App
#### GET
```
$ curl -i http://localhost:8080/product/13860428
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 09 Apr 2022 22:43:31 GMT

{"id":"13860428","name":"The Big Lebowski (Blu-ray)","currentPrice":1.23,"currencyCode":"USD"}
```
#### PUT
```
$ curl -i -X PUT -d '{"id":"13860428","name":"The Big Lebowski (Blu-ray)","currentPrice":9.99,"currencyCode":"EUR"}' -H "Content-Type: application/json" http://localhost:8080/product/13860428
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 09 Apr 2022 22:47:18 GMT

{"id":"13860428","name":"The Big Lebowski (Blu-ray)","currentPrice":9.99,"currencyCode":"EUR"}

```
