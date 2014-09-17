boot-microservice
=======================

Example of a microservice that works on Spring Boot.

## Requirements

* JDK8 v.05

## Introduction

To make setting up microservices in micro-time we needed to extract the common building blocks to separate libraries.
In this template we are using the following 4finance custom libraries (available in jCenter)

* ["Micro Deps Spring Config" library (micro-deps-spring-config module)](https://github.com/4finance/micro-deps-spring-config)
* ["Micro Infra Spring"](https://github.com/4finance/micro-infra-spring). 
* ["Micro Deps Spring Config" library (micro-deps-spring-test-config module)](https://github.com/4finance/micro-deps-spring-config)

##How it works?

###Production code

#### Configuration

Below you can find description of the most crucial parts of the application's production code.

##### *com.ofg.microservice.Application* 

contains Spring Boot autoconfiguration and contains *main* method

##### *com.ofg.microservice.config.ServiceDiscoveryConfiguration* 

imports configuration from [4finance's "Micro Deps Spring Config" library (micro-deps-spring-config module)](https://github.com/4finance/micro-deps-spring-config) 
that contains Service Discovery configuration. For Service Discovery we are using Zookeeper from [4finance's "Micro Deps" library](https://github.com/4finance/micro-deps).
Note: **This configuration should not be imported in the same profiles as tests since the application will try to connect to a running Zookeeper instance.**

##### *com.ofg.microservice.config.WebAppConfiguration*

imports configuration from [4finance's "Micro Infra Spring"](https://github.com/4finance/micro-infra-spring). That module contains all common web configuration like
Swagger, CorrelationId filters, custom RestTemplate, custom exception handling, health check controllers etc.

### Tests

Below you can find description of the most crucial parts of the application's test code. 


##### *com.ofg.base.ServiceDiscoveryStubbingApplicationConfiguration*

configuration that is a point of entry for all the integration tests. It imports the *com.ofg.infrastructure.discovery.ServiceDiscoveryStubbingConfiguration* configuration
that stubs the Zookeeper server with a map of *dependency names* vs *dependency address*.

##### *com.ofg.base.MicroserviceIntegrationSpec*

extends *com.ofg.infrastructure.base.IntegrationSpec* Spock *Specification* class that initializes Spring web-context. 

##### *com.ofg.base.MicroserviceMvcIntegrationSpec*

extends *com.ofg.infrastructure.base.MvcIntegrationSpec* Spock *Specification* class that initializes Spring web-context and provides some autowired fields including the
*ServiceProvider* interface that allows you to stub Zookeeper entries.

##### *com.ofg.base.MicroserviceMvcWiremockSpec*

extends *com.ofg.infrastructure.base.MvcWiremockIntegrationSpec* Spock *Specification* class that extends the *MvcIntegrationSpec* spec. Additionally it provides 
[WireMock](http://wiremock.org/) related fields and methods.


##Sample business requirement
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
