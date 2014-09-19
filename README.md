boot-microservice
=======================

Example of a microservice that works on Spring Boot.

## Requirements

* JDK8 v.05

## Introduction

To make setting up microservices in micro-time we needed to extract the common building blocks to separate libraries.
In this template we are using the following 4finance custom libraries (available in jcenter)

[Micro Infra Spring](https://github.com/4finance/micro-infra-spring). 

[Micro Deps Spring Config library (micro-deps-spring-test-config module)](https://github.com/4finance/micro-deps-spring-config#micro-deps-spring-test-config)

## How it works?

### Production code

#### Configuration

Below you can find description of the most crucial parts of the application's production code.

##### Application 

contains Spring Boot autoconfiguration and contains *main* method

##### WebAppConfiguration

imports configuration from [4finance's "Micro Infra Spring"](https://github.com/4finance/micro-infra-spring). That module contains all common web configuration like
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


## Sample business requirement
Twitter places analyzer, searches through tweets for places. Then analyzers send those to Collerators.

INPUT
-----------------

Hit *PUT* at: 

```
/api/{pairId}
```

with list of tweets:

```
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

```
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
