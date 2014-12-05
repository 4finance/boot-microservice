package com.ofg.twitter.tweets

class Tweets {

    public static String TWEET_WITH_PLACE = '''
    {
        "created_at": "Sat Jul 26 09:38:57 +0000 2014",
        "id": 492967299297845248,
        "id_str": "492967299297845248",
        "text": "Gonna see you at Warsaw",
        "place":
        {
            "attributes":{},
            "bounding_box":
            {
                "coordinates":
                    [[
                        [-77.119759,38.791645],
                        [-76.909393,38.791645],
                        [-76.909393,38.995548],
                        [-77.119759,38.995548]
                    ]],
                "type":"Polygon"
            },
            "country":"United States",
            "country_code":"US",
            "full_name":"Washington, DC",
            "id":"01fbe706f872cb32",
            "name":"Washington",
            "place_type":"city",
            "url": "http://api.twitter.com/1/geo/id/01fbe706f872cb32.json"
        }
    }
'''

    public static String TWEET_WITHOUT_A_PLACE = '''
    {
        "created_at": "Sat Jul 26 09:38:57 +0000 2014",
        "id": 492967299297845248,
        "id_str": "492967299297845248",
        "text": "Gonna see you at Warsaw",
        "place": null
    }
'''

    public static String TWEET_WITH_COORDINATES = '''
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
        }
    }
'''

    public static String TWEET_WITHOUT_COORDINATES = '''
    {
        "created_at": "Sat Jul 26 09:15:10 +0000 2014",
        "id": 492961315070439424,
        "id_str": "492961315070439424",
        "geo": null
    }
'''

}
