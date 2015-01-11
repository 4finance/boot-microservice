# boot-microservice [![Build Status](https://travis-ci.org/4finance/boot-microservice.svg?branch=master)](https://travis-ci.org/4finance/boot-microservice) [![Coverage Status](http://img.shields.io/coveralls/4finance/boot-microservice/master.svg)](https://coveralls.io/r/4finance/boot-microservice)

Example of a microservice that works on Spring Boot.

## Requirements

* Java 8

## Introduction

To make setting up microservices in micro-time we needed to extract the common building blocks to separate libraries.
In this template we are using the following 4finance custom libraries (available in jcenter)

[Micro Infra Spring](https://github.com/4finance/micro-infra-spring).

## What should I do after I clone the repo?

You can remove all the caching related libraries, annotations and **com.ofg.twitter** packages.
Next adjust all the properties and then you have an empty project - you're now ready to go.

## How can I run it?

### From jar

To run it just
 - create a jar - `./gradlew clean build`
 - go to `build/libs` to find the fat jar
 - execute `java -jar` like presented below

for production mode (no stubs)
```
java -Dspring.profiles.active=prod -jar boot-microservice.jar
```

for developer mode (stubs and embedded Zookeeper)
```
java -Dspring.profiles.active=dev -jar boot-microservice.jar
```

### From gradle

you can also run it directly using gradle like this:

for production mode (no stubs)

```
./gradlew bootRun -Dspring.profiles.active=prod
```

for developer mode (stubs and embedded Zookeeper)

```
./gradlew bootRun -Dspring.profiles.active=dev
```

### From Docker

#### Development

Running:

```
./gradlew docker
```

will create `/build/docker/Dockerfile`. You can use this file to create [Docker](https://www.docker.com/) image:

```
sudo docker build -t boot-microservice build/docker
```

Self-sufficient Docker image with our sample microservice can be started as follows:

```
docker run -e spring.profiles.active=dev -p 8080:8095 boot-microservice
```

Test with `curl localhost:8080/ping`. Notice that we run it in `dev` profile (in-memory embedded ZooKeeper and stubs) and we re-map 8095 port to 8080 on host machine.

#### Standalone ZooKeeper

If you want to run microservice with real service discovery via ZooKeeper, first prepare Docker image for that:

```
docker run --name zookeeper jplock/zookeeper
```

After the first time this container can be executed with `docker start -a zookeeper` shorthand. Now you can run arbitrary number of microservices and they will all register themselves in ZooKeeper instance:

```
docker run \
  -e spring.profiles.active=prod \
  --link zookeeper:zk \
  boot-microservice \
  --service.resolver.url=zk:2181
```
We link microservice container with ZooKeeper container aliasing it to `zk`. This way microservice sees ZooKeeper container under `zk` hostname and we can simply point `service.resolver.url=zk:2181` (`zk:2181` is a valid network address from `boot-microservice` container's perspective).

## How it works?

### Production code

#### Configuration

Below you can find description of the most crucial parts of the application's production code.

##### Application 

contains Spring Boot autoconfiguration and contains *main* method

##### Microservice Autoconfiguration

Spring boot auto-configure application and enable support for [4finance's "Micro Infra Spring"](https://github.com/4finance/micro-infra-spring). That enables module like
ServiceDiscovery, Swagger, CorrelationId filters, custom RestTemplate, custom exception handling, health check controllers etc.
Check out [Micro Infra Spring's Boot Starter module's Spring factories set up](https://github.com/4finance/micro-infra-spring/blob/master/micro-infra-spring-boot-starter/src/main/resources/META-INF/spring.factories)

If you want only certain modules of the system just check out [4finance's Micro Infra Spring's readme](https://github.com/4finance/micro-infra-spring).

### Tests

Below you can find description of the most crucial parts of the application's test code. 

##### MicroserviceIntegrationSpec

extends *com.ofg.infrastructure.base.IntegrationSpec* Spock *Specification* class that initializes Spring web-context. 

##### MicroserviceMvcIntegrationSpec

extends *com.ofg.infrastructure.base.MvcIntegrationSpec* Spock *Specification* class that initializes Spring web-context and provides some additional Mvc related fields.

##### MicroserviceMvcWiremockSpec

extends *com.ofg.infrastructure.base.MvcWiremockIntegrationSpec* Spock *Specification* class that extends the *MvcIntegrationSpec* spec. Additionally it provides 
[WireMock](http://wiremock.org/) related fields and methods.

## Consumer Driven Contracts

you may wonder - how on earth does the collaborator *collerator* respond to with 200 when you post him at /1 ?! It's all about 
[Consumer Driven Contracts](http://martinfowler.com/articles/consumerDrivenContracts.html) and our implementation called 
[stub-runner-spring](https://github.com/4finance/stub-runner-spring/wiki/How-to-use-it).

What happens under the hood is that the stubs are downloaded from 4finance Bintray account. A jar of the 
[stub-runner-examples](https://github.com/4finance/stub-runner-examples) is downloaded and unpacked to a temporary folder and all the
tests are ran against it. The stub is in fact here [twitter-places-collerator stub](https://github.com/4finance/stub-runner-examples/blob/master/repository/mappings/com/ofg/twitter-places-collerator/findPlaceByPairId.json)!

The paths to the repo, the module name and artifactId are here - [application.properties](src/main/resources/application.properties).

## Sample business requirement

Twitter places analyzer, searches through tweets for places. Then analyzers send those to Collerators.

INPUT
-----------------

Hit *PUT* at: 

```
/api/{pairId}
```

with list of tweets:

```json
[
    {
        "created_at": "Sat Jul 26 09:15:10 +0000 2014",
        "id": 492961315070439424,
        "id_str": "492961315070439424",
        "geo": null,
        "coordinates":
        {
            "coordinates":
                [
                    -75.14310264,
                    40.05701649
                ],
            "type":"Point"
        },
    },
    {
            "created_at": "Sat Jul 26 09:15:10 +0000 2014",
            "id": 492961315070439424,
            "id_str": "492961315070439424",
            "geo": null,
            "coordinates":
            {
                "coordinates":
                    [
                        -75.14310264,
                        40.05701649
                    ],
                "type":"Point"
            },
     }
]
```

OUTPUT
-----------------

And it will hit collectors at /{pairId} with tweets taken from twitter

```json
[
    {
        "pair_id" : 1,
        "tweet_id" : "492967299297845248",
        "place" :
        {
            "name":"Washington",
            "country_code": "US"
        },
        "probability" : "2",
        "origin" : "twitter_place_section"
    },
    {
        "pair_id" : 2,
        "tweet_id" : "123187246819263981"
    },
]
```

## Build status
[![Build Status](https://travis-ci.org/4finance/boot-microservice.svg?branch=master)](https://travis-ci.org/4finance/boot-microservice) [![Coverage Status](http://img.shields.io/coveralls/4finance/boot-microservice/master.svg)](https://coveralls.io/r/4finance/boot-microservice)
