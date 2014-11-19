boot-microservice
=======================

Example of a microservice that works on Spring Boot.

## Requirements

* Java 8

## Introduction

To make setting up microservices in micro-time we needed to extract the common building blocks to separate libraries.
In this template we are using the following 4finance custom libraries (available in jcenter)

[Micro Infra Spring](https://github.com/4finance/micro-infra-spring). 

[Micro Deps Spring Config library (micro-deps-spring-test-config module)](https://github.com/4finance/micro-deps-spring-config#micro-deps-spring-test-config)

## What should I do after I clone the repo?

You can remove all the caching related libraries, annotations and **com.ofg.twitter** packages.
Next adjust all the properties and then you have an empty project - you're now ready to go.

## How it works?

### Production code

#### Configuration

Below you can find description of the most crucial parts of the application's production code.

##### Application 

contains Spring Boot autoconfiguration and contains *main* method

##### Microservice Autoconfiguration

Spring boot auto-configure application and enable support for [4finance's "Micro Infra Spring"](https://github.com/4finance/micro-infra-spring). That enables module like
ServiceDiscovery, Swagger, CorrelationId filters, custom RestTemplate, custom exception handling, health check controllers etc.

If you want only certain modules of the system just check out [4finance's Micro Infra Spring's readme](https://github.com/4finance/micro-infra-spring).

### Tests

Below you can find description of the most crucial parts of the application's test code. 

##### MicroserviceIntegrationSpec

extends *com.ofg.infrastructure.base.IntegrationSpec* Spock *Specification* class that initializes Spring web-context. 

##### MicroserviceMvcIntegrationSpec

extends *com.ofg.infrastructure.base.MvcIntegrationSpec* Spock *Specification* class that initializes Spring web-context and provides some autowired fields including the
*ServiceProvider* interface that allows you to stub Zookeeper entries.

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
