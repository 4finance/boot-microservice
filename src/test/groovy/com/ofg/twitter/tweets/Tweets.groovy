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
        },
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

    public static String TWEET_WITH_USER_MENTION_THAT_IS_NOT_A_CITY = '''
[{
        "created_at": "Sat Jul 26 09:05:22 +0000 2014",
        "id": 492958847460716544,
        "id_str": "492958847460716544",
        "text": "@tgrall go, go, go !",
        "source": "\\u003ca href=\\"http:\\/\\/itunes.apple.com\\/us\\/app\\/twitter\\/id409789998?mt=12\\" rel=\\"nofollow\\"\\u003eTwitter for Mac\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": 492952779762462720,
        "in_reply_to_status_id_str": "492952779762462720",
        "in_reply_to_user_id": 14338755,
        "in_reply_to_user_id_str": "14338755",
        "in_reply_to_screen_name": "tgrall",
        "user": {
            "id": 14441858,
            "id_str": "14441858",
            "name": "Guillaume Laforge",
            "screen_name": "glaforge",
            "location": "Paris, France",
            "description": "Groovy project manager",
            "url": "http:\\/\\/t.co\\/8ieqmn4cfT",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/8ieqmn4cfT",
                            "expanded_url": "http:\\/\\/glaforge.appspot.com",
                            "display_url": "glaforge.appspot.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 8431,
            "friends_count": 309,
            "listed_count": 642,
            "created_at": "Sat Apr 19 09:10:53 +0000 2008",
            "favourites_count": 1052,
            "utc_offset": 7200,
            "time_zone": "Paris",
            "geo_enabled": true,
            "verified": false,
            "statuses_count": 24208,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "1A1B1F",
            "profile_background_image_url": "http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
            "profile_background_image_url_https": "https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/480309342135017472\\/kveVXIxM_normal.png",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/480309342135017472\\/kveVXIxM_normal.png",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/14441858\\/1398198938",
            "profile_link_color": "2FC2EF",
            "profile_sidebar_border_color": "181A1E",
            "profile_sidebar_fill_color": "252429",
            "profile_text_color": "666666",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 0,
        "favorite_count": 0,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "urls": [],
            "user_mentions": [
                {
                    "screen_name": "tgrall",
                    "name": "Tugdual Grall",
                    "id": 14338755,
                    "id_str": "14338755",
                    "indices": [0, 7]
                }
            ]
        },
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    }]
'''

    public static String TWEET_WITH_USER_MENTION_THAT_IS_A_CITY = '''
[{
        "created_at": "Sat Jul 26 09:05:22 +0000 2014",
        "id": 492958847460716544,
        "id_str": "492958847460716544",
        "text": "@tgrall go, go, go !",
        "source": "\\u003ca href=\\"http:\\/\\/itunes.apple.com\\/us\\/app\\/twitter\\/id409789998?mt=12\\" rel=\\"nofollow\\"\\u003eTwitter for Mac\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": 492952779762462720,
        "in_reply_to_status_id_str": "492952779762462720",
        "in_reply_to_user_id": 14338755,
        "in_reply_to_user_id_str": "14338755",
        "in_reply_to_screen_name": "tgrall",
        "user": {
            "id": 14441858,
            "id_str": "14441858",
            "name": "Guillaume Laforge",
            "screen_name": "glaforge",
            "location": "Paris, France",
            "description": "Groovy project manager",
            "url": "http:\\/\\/t.co\\/8ieqmn4cfT",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/8ieqmn4cfT",
                            "expanded_url": "http:\\/\\/glaforge.appspot.com",
                            "display_url": "glaforge.appspot.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 8431,
            "friends_count": 309,
            "listed_count": 642,
            "created_at": "Sat Apr 19 09:10:53 +0000 2008",
            "favourites_count": 1052,
            "utc_offset": 7200,
            "time_zone": "Paris",
            "geo_enabled": true,
            "verified": false,
            "statuses_count": 24208,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "1A1B1F",
            "profile_background_image_url": "http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
            "profile_background_image_url_https": "https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/480309342135017472\\/kveVXIxM_normal.png",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/480309342135017472\\/kveVXIxM_normal.png",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/14441858\\/1398198938",
            "profile_link_color": "2FC2EF",
            "profile_sidebar_border_color": "181A1E",
            "profile_sidebar_fill_color": "252429",
            "profile_text_color": "666666",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 0,
        "favorite_count": 0,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "urls": [],
            "user_mentions": [
                {
                    "screen_name": "Warszawa",
                    "name": "Warszawa",
                    "id": 14338755,
                    "id_str": "14338755",
                    "indices": [0, 7]
                }
            ]
        },
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    }]
'''
    
    public static final String TWEET_WITH_A_HASH_TAG_THAT_IS_NOT_A_CITY = '''
[{
        "created_at": "Sat Jul 26 09:00:07 +0000 2014",
        "id": 492957523704414208,
        "id_str": "492957523704414208",
        "text": "Thoughtworker @dpjoyce Learning to See, Learning to Lead; The Vanguard Method #agileafrica - http:\\/\\/t.co\\/gNShY4BVdl",
        "source": "\\u003ca href=\\"http:\\/\\/sproutsocial.com\\" rel=\\"nofollow\\"\\u003eSprout Social\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": null,
        "in_reply_to_status_id_str": null,
        "in_reply_to_user_id": null,
        "in_reply_to_user_id_str": null,
        "in_reply_to_screen_name": null,
        "user": {
            "id": 23009949,
            "id_str": "23009949",
            "name": "ThoughtWorks",
            "screen_name": "thoughtworks",
            "location": "",
            "description": "A community of passionate individuals whose purpose is to revolutionize software design, creation and delivery, while advocating for positive social change.",
            "url": "http:\\/\\/t.co\\/MfDvjQZ9Ut",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/MfDvjQZ9Ut",
                            "expanded_url": "http:\\/\\/www.thoughtworks.com",
                            "display_url": "thoughtworks.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 20740,
            "friends_count": 7594,
            "listed_count": 767,
            "created_at": "Fri Mar 06 01:20:09 +0000 2009",
            "favourites_count": 859,
            "utc_offset": -18000,
            "time_zone": "Central Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 6974,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "FFFFFF",
            "profile_background_image_url": "http:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000164187732\\/4zhDcyvE.png",
            "profile_background_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000164187732\\/4zhDcyvE.png",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/459401977462407168\\/reORxIro_normal.png",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/459401977462407168\\/reORxIro_normal.png",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/23009949\\/1404922780",
            "profile_link_color": "F867AC",
            "profile_sidebar_border_color": "FFFFFF",
            "profile_sidebar_fill_color": "A7A7AA",
            "profile_text_color": "292929",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 0,
        "favorite_count": 1,
        "entities": {
            "hashtags": [
                {
                    "text": "agileafrica",
                    "indices": [78, 90]
                }
            ],
            "symbols": [],
            "urls": [
                {
                    "url": "http:\\/\\/t.co\\/gNShY4BVdl",
                    "expanded_url": "http:\\/\\/bit.ly\\/1zWKAi6",
                    "display_url": "bit.ly\\/1zWKAi6",
                    "indices": [93, 115]
                }
            ],
            "user_mentions": null
        },
        "favorited": false,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "en"
    }]
'''

    public static final String TWEET_WITH_A_HASH_TAG_THAT_IS_A_CITY = '''
[{
        "created_at": "Sat Jul 26 09:00:07 +0000 2014",
        "id": 492957523704414208,
        "id_str": "492957523704414208",
        "text": "Thoughtworker @dpjoyce Learning to See, Learning to Lead; The Vanguard Method #agileafrica - http:\\/\\/t.co\\/gNShY4BVdl",
        "source": "\\u003ca href=\\"http:\\/\\/sproutsocial.com\\" rel=\\"nofollow\\"\\u003eSprout Social\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": null,
        "in_reply_to_status_id_str": null,
        "in_reply_to_user_id": null,
        "in_reply_to_user_id_str": null,
        "in_reply_to_screen_name": null,
        "user": {
            "id": 23009949,
            "id_str": "23009949",
            "name": "ThoughtWorks",
            "screen_name": "thoughtworks",
            "location": "",
            "description": "A community of passionate individuals whose purpose is to revolutionize software design, creation and delivery, while advocating for positive social change.",
            "url": "http:\\/\\/t.co\\/MfDvjQZ9Ut",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/MfDvjQZ9Ut",
                            "expanded_url": "http:\\/\\/www.thoughtworks.com",
                            "display_url": "thoughtworks.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 20740,
            "friends_count": 7594,
            "listed_count": 767,
            "created_at": "Fri Mar 06 01:20:09 +0000 2009",
            "favourites_count": 859,
            "utc_offset": -18000,
            "time_zone": "Central Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 6974,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "FFFFFF",
            "profile_background_image_url": "http:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000164187732\\/4zhDcyvE.png",
            "profile_background_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000164187732\\/4zhDcyvE.png",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/459401977462407168\\/reORxIro_normal.png",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/459401977462407168\\/reORxIro_normal.png",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/23009949\\/1404922780",
            "profile_link_color": "F867AC",
            "profile_sidebar_border_color": "FFFFFF",
            "profile_sidebar_fill_color": "A7A7AA",
            "profile_text_color": "292929",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 0,
        "favorite_count": 1,
        "entities": {
            "hashtags": [
                {
                    "text": "Warszawa",
                    "indices": [78, 90]
                }
            ],
            "symbols": [],
            "urls": [
                {
                    "url": "http:\\/\\/t.co\\/gNShY4BVdl",
                    "expanded_url": "http:\\/\\/bit.ly\\/1zWKAi6",
                    "display_url": "bit.ly\\/1zWKAi6",
                    "indices": [93, 115]
                }
            ],
            "user_mentions": null
        },
        "favorited": false,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "en"
    }]
'''

    public static final String TWEET_WITH_TEXT_WITHOUT_A_CITY = '''
[{
        "created_at": "Sat Jul 26 08:15:11 +0000 2014",
        "id": 492946218008322048,
        "id_str": "492946218008322048",
        "text": "\\u201cLinkedIn favours profiles that are 100% complete\\u201d http:\\/\\/t.co\\/dDHhbqtEj6 Tips to get found",
        "source": "\\u003ca href=\\"http:\\/\\/www.hootsuite.com\\" rel=\\"nofollow\\"\\u003eHootsuite\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": null,
        "in_reply_to_status_id_str": null,
        "in_reply_to_user_id": null,
        "in_reply_to_user_id_str": null,
        "in_reply_to_screen_name": null,
        "user": {
            "id": 17093617,
            "id_str": "17093617",
            "name": "Hootsuite",
            "screen_name": "hootsuite",
            "location": "Vancouver, Canada",
            "description": "Social media news and tips from the world\\u2019s most widely used social relationship platform. Sign up for free: http:\\/\\/t.co\\/Zmrb0hY23s Support: @Hootsuite_Help",
            "url": "http:\\/\\/t.co\\/3Xvuxw2JEG",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/3Xvuxw2JEG",
                            "expanded_url": "http:\\/\\/blog.hootsuite.com",
                            "display_url": "blog.hootsuite.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/Zmrb0hY23s",
                            "expanded_url": "http:\\/\\/ow.ly\\/uulpL",
                            "display_url": "ow.ly\\/uulpL",
                            "indices": [109, 131]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 6025005,
            "friends_count": 1606764,
            "listed_count": 38588,
            "created_at": "Fri Oct 31 22:26:54 +0000 2008",
            "favourites_count": 2234,
            "utc_offset": -25200,
            "time_zone": "Pacific Time (US & Canada)",
            "geo_enabled": false,
            "verified": true,
            "statuses_count": 13022,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "FFFFFF",
            "profile_background_image_url": "http:\\/\\/pbs.twimg.com\\/profile_background_images\\/491618833099014145\\/iT7oZe6f.png",
            "profile_background_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_background_images\\/491618833099014145\\/iT7oZe6f.png",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/473952620160090113\\/nlkoo80h_normal.jpeg",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/473952620160090113\\/nlkoo80h_normal.jpeg",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/17093617\\/1401834341",
            "profile_link_color": "00AEEF",
            "profile_sidebar_border_color": "FFFFFF",
            "profile_sidebar_fill_color": "EEEEEE",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 40,
        "favorite_count": 31,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "urls": [
                {
                    "url": "http:\\/\\/t.co\\/dDHhbqtEj6",
                    "expanded_url": "http:\\/\\/ow.ly\\/zw6GL",
                    "display_url": "ow.ly\\/zw6GL",
                    "indices": [51, 73]
                }
            ],
            "user_mentions": []
        },
        "favorited": false,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "en"
    }]
'''

    public static final String TWEET_WITH_TEXT_WITH_A_CITY = '''
[{
        "created_at": "Sat Jul 26 08:15:11 +0000 2014",
        "id": 492946218008322048,
        "id_str": "492946218008322048",
        "text": "\\u201cLinkedIn favours profiles that are 100% complete\\u201d http:\\/\\/t.co\\/dDHhbqtEj6 Tips to get found",
        "source": "\\u003ca href=\\"http:\\/\\/www.hootsuite.com\\" rel=\\"nofollow\\"\\u003eHootsuite\\u003c\\/a\\u003e",
        "truncated": false,
        "in_reply_to_status_id": null,
        "in_reply_to_status_id_str": null,
        "in_reply_to_user_id": null,
        "in_reply_to_user_id_str": null,
        "in_reply_to_screen_name": null,
        "user": {
            "id": 17093617,
            "id_str": "17093617",
            "name": "Hootsuite",
            "screen_name": "hootsuite",
            "location": "Vancouver, Canada",
            "description": "Social media news and tips from the world\\u2019s most widely used social relationship platform. Sign up for free: http:\\/\\/t.co\\/Zmrb0hY23s Support: @Hootsuite_Help",
            "url": "http:\\/\\/t.co\\/3Xvuxw2JEG",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/3Xvuxw2JEG",
                            "expanded_url": "http:\\/\\/blog.hootsuite.com",
                            "display_url": "blog.hootsuite.com",
                            "indices": [0, 22]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "http:\\/\\/t.co\\/Zmrb0hY23s",
                            "expanded_url": "http:\\/\\/ow.ly\\/uulpL",
                            "display_url": "ow.ly\\/uulpL",
                            "indices": [109, 131]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 6025005,
            "friends_count": 1606764,
            "listed_count": 38588,
            "created_at": "Fri Oct 31 22:26:54 +0000 2008",
            "favourites_count": 2234,
            "utc_offset": -25200,
            "time_zone": "Pacific Time (US & Canada)",
            "geo_enabled": false,
            "verified": true,
            "statuses_count": 13022,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "FFFFFF",
            "profile_background_image_url": "http:\\/\\/pbs.twimg.com\\/profile_background_images\\/491618833099014145\\/iT7oZe6f.png",
            "profile_background_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_background_images\\/491618833099014145\\/iT7oZe6f.png",
            "profile_background_tile": false,
            "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/473952620160090113\\/nlkoo80h_normal.jpeg",
            "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/473952620160090113\\/nlkoo80h_normal.jpeg",
            "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/17093617\\/1401834341",
            "profile_link_color": "00AEEF",
            "profile_sidebar_border_color": "FFFFFF",
            "profile_sidebar_fill_color": "EEEEEE",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "retweet_count": 40,
        "favorite_count": 31,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "urls": [
                {
                    "url": "http:\\/\\/t.co\\/dDHhbqtEj6",
                    "expanded_url": "http:\\/\\/ow.ly\\/zw6GL",
                    "display_url": "ow.ly\\/zw6GL",
                    "indices": [51, 73]
                }
            ],
            "user_mentions": []
        },
        "favorited": false,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "en"
    }]
'''

}
