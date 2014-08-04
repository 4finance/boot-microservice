twitter-places-analyzer
=======================

Twitter places analyzer, searches through tweets for places. Then analyzers send those to Collerators.

INPUT
-----------------

Hit *PUT* at: 

```
/{pairId}
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
[![Build Status](https://travis-ci.org/microhackaton/twitter-places-analyzer.svg?branch=master)](https://travis-ci.org/microhackaton/twitter-places-analyzer) [![Coverage Status](http://img.shields.io/coveralls/microhackaton/twitter-places-analyzer/master.svg)](https://coveralls.io/r/microhackaton/twitter-places-analyzer)
