package com.ofg.twitter.places

import com.ofg.base.MvcWiremockIntegrationSpec

class AcceptanceSpec extends MvcWiremockIntegrationSpec {

    def "should find a place by verifying tweet's geolocation"() {
        given: 'a tweet with a coordinates section filled in'
        when: 'trying to retrieve place from the tweet'
        then: "user's location (place) will be extracted from that section"
    }

    def "should find a place by verifying tweet's place"() {
        given: 'a tweet with a places section filled in'
        when: 'trying to retrieve place from the tweet'
        then: "user's location (place) will be extracted from that section"
    }

    def "should try to find a place by cross referencing tweet's user mention with a city index if coordinates/places is not available"() {
        given: 'a tweet with a user mention section filled in (without coordinates)'
        when: 'trying to retrieve place from the tweet by cross referencing user mention with a city index'
        then: "user's location (place) will be the matching city"
    }

    def "should try to find a place by cross referencing tweet's hashtags with a city index if no user mention matches"() {
        given: 'a tweet with a hashtag section filled in (without coordinates/places and user mention)'
        when: 'trying to retrieve place from the tweet by cross referencing hash tag with a city index'
        then: "user's location (place) will be the matching city"
    }

    def "should try to find a place by cross referencing tweet's content with a city index if no user mention matches"() {
        given: 'a tweet without geolocation, places, user mention and hashtag sections'
        when: 'trying to retrieve place from the tweet by cross referencing tweet content with a city index'
        then: "user's location (place) will be the first matching city"
    }

}
