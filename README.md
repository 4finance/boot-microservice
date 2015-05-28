# boot-microservice [![Build Status](https://travis-ci.org/4finance/boot-microservice.svg?branch=master)](https://travis-ci.org/4finance/boot-microservice) [![Coverage Status](http://img.shields.io/coveralls/4finance/boot-microservice/master.svg)](https://coveralls.io/r/4finance/boot-microservice)

Example of a microservice that works on Spring Boot.

## Requirements

* Java 8

## Introduction

To make setting up microservices in micro-time we needed to extract the common building blocks to separate libraries.
In this template we are using 4finance custom library, i.e. [micro-infra-spring](https://github.com/4finance/micro-infra-spring) (available in jcenter).

## GUI version

There is a version with web application connected with backend side. You can find it on the branch of this repo: [boot-microservice-gui](https://github.com/4finance/boot-microservice/tree/boot-microservice-gui).

## What should I do after I clone the repo?

You can remove all the caching related libraries, annotations and **com.ofg.twitter** packages.
Next adjust all the properties and then you have an empty project - you're now ready to go.

## How can I run it?

### From script

Execute [run.sh](scripts/run.sh) or [run.bat](scripts/run.bat) script.

### From jar

To run it just

* create a jar - `./gradlew clean build`
* go to `build/libs` to find the fat jar
* execute `java -jar` with the same arguments as in runner script

```
java -jar boot-microservice.jar runnerArgs
```

### From gradle

Please check [run.sh](scripts/run.sh) or [run.bat](scripts/run.bat) script.

### From IntelliJ IDEA

Run Application class with the following VM args:

```
-DAPP_ENV="prod" -DCONFIG_FOLDER="properties" -Dencrypt.key="secretEncryptKey" -Dspring.profiles.active=dev
```

For details check runner script.

### From Docker

#### Development

Running:

```
./gradlew docker
```

will create `/build/docker/Dockerfile`. You can use this file to create [Docker](https://www.docker.com/) image:

```
docker build -t boot-microservice build/docker
```

Self-sufficient Docker image with our sample microservice can be started as follows:

```
docker run \
    -e spring.profiles.active=dev \
    -e APP_ENV=prod \
    -e ENCRYPT_KEY=secretEncryptKey \
    -p 8080:8095 \
    boot-microservice
```

Test with `curl localhost:8080/ping`. Notice that we run it in `dev` profile (in-memory embedded ZooKeeper and stubs) and we re-map 8095 port to 8080 on host machine.

#### Standalone setup with ZooKeeper and Graphite

If you want to run microservice with real service discovery via ZooKeeper and Graphite, first prepare Docker images for that:

```
docker run --name zookeeper jplock/zookeeper
docker run --name graphite -p 8081:80 kamon/grafana_graphite
```

After the first time these containers can be executed with `docker start -a zookeeper` and `docker start -a graphite` shorthands. Now you can run arbitrary number of microservices and they will all register themselves in ZooKeeper/Graphite instances:

```
docker run \
  -p 8080:8080 \
  -e spring.profiles.active=prod \
  --link zookeeper:zk \
  --link graphite:gr \
  boot-microservice \
  --service.resolver.url=zk:2181 \
  --graphite.host=gr
```

We link microservice container with ZooKeeper container aliasing it to `zk`. This way microservice sees ZooKeeper container under `zk` hostname and we can simply point `service.resolver.url=zk:2181` (`zk:2181` is a valid network address from `boot-microservice` container's perspective). Same applies to Graphite.

Your microservice (assuming it exposes 8080 port) will be visible outside under 8080 as well. Moreover you can browse to `localhost:8081` and browse Grafana.

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

The paths to the repo, the module name and artifactId are here - [twitter-places-analyzer.yaml](properties/common/com/ofg/twitter-places-analyzer.yaml).

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

# boot-microservice-gui [![Build Status](https://travis-ci.org/4finance/boot-microservice.svg?branch=boot-microservice-gui)](https://travis-ci.org/4finance/boot-microservice) [![Coverage Status](http://img.shields.io/coveralls/4finance/boot-microservice/boot-microservice-gui.svg)](https://coveralls.io/r/4finance/boot-microservice?branch=boot-microservice-gui)

This is an extension of the non-GUI boot-microservice. To read about the backend side, please refer to boot-microservice.

## GUI

The webapp is generated by [yeoman](http://yeoman.io/) using the [angular generator](https://github.com/yeoman/generator-angular).

It uses grunt that will automatically 'compile' your whole application, allowing cool dev mode with live reloads.

Inside you will find [bower](http://bower.io/) for javascript dependency management and [node](http://nodejs.org/) with
[npm](https://www.npmjs.org/) that grunt uses.

### Development mode

Before first use, build your whole application with `gradle build`. It will download auto-magically all npms and bower dependencies.

Then run you application (for example from Idea, just run main in `com.ofg.twitter.Application` specifying
the correct -Dspring.profiles.active).

Now your application (backend) works. But you still need js+html. And since this is 2015, you don't just write html anymore, you have to use a shitload of libs :)

Install npm if you don't have it already. For example on Debian-based linux run:
```
sudo apt-get install npm
```

Then install grunt.
```
sudo npm install -g grunt-cli
```

And now make a symbolic link, because nodejs from Debian repos has a wrong name
```
ln -s /usr/bin/nodejs /usr/bin/node
```

Next, go to `src/main/web` and type `npm install`. This will download all needed libraries defined in `package.json` file and install bower components at the end. After this you can finally `grunt serve`. This will run a local webserver on port 9000, your application will
automatically open in the browser and from now on on every change in you webapp the browser will automatically refresh
(no need to hit cmd-R all the time!).

Easy, right? Writing HTML in 2015 is simple... nooooot! :D

### Production mode

When you build your application there is a special directory `src/main/web/dist` created, where grunt puts your minified, compacted
and production-ready application. Then the whole folder will be bundled in your jar's `static` folder which will make
it available when you run the jar.

### Calling your microservice REST services

Once you expose some REST services on your backend, you will probably want to call them from Angular.

To make it possible in the Development mode, you will have to expose them via proxy (your dev page is available
on port 9000 while the app is on 8080, remember?). Look into `src/main/web/Gruntfile.js` and inside `connect` you will have
`proxies` section like this

```js
connect: {
            proxies: [
                {context: '/info', host: 'localhost', port: 8080},
                {context: '/city', host: 'localhost', port: 8080},
                {context: '/api', host: 'localhost', port: 8080}
            ],
```

Just add whatever you wish. If you don't like exposing every service explicitly, you can expose them all in spring under some
 common path like /rest, and then you have to specify only the /rest in the proxy.

### Cleaning npm and bower deps

 Type `gradle cleanGUIDeps`
 
