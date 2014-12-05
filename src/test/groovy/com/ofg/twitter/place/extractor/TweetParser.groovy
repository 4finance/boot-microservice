package com.ofg.twitter.place.extractor

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ofg.twitter.place.model.Tweet

class TweetParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    static Tweet parseTweet(String tweet) {
        return OBJECT_MAPPER.readValue(tweet, Tweet)
    }

    static List<Tweet> parseTweets(String tweets) {
        return OBJECT_MAPPER.readValue(tweets, new TypeReference<List<Tweet>>(){})
    }
}
