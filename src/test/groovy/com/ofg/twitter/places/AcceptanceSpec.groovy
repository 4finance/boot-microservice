package com.ofg.twitter.places

import com.ofg.base.MvcWiremockIntegrationSpec

class AcceptanceSpec extends MvcWiremockIntegrationSpec {

    def "should find a place by verifying tweet's geolocation"() {
        given: 'a tweet with a geolocation section filled in'
        when: 'trying to retrieve place from the tweet'
        then: "user's location (place) will be extracted from that section"
    }

    def "should try to find a place by cross referencing tweet's user mention with a city index if geolocation is not available"() {
        given: 'a tweet with a user mention section filled in (without geolocation)'
        when: 'trying to retrieve place from the tweet by cross referencing user mention with a city index'
        then: "user's location (place) will be the matching city"
    }

    def "should try to find a place by cross referencing tweet's hashtags with a city index if no user mention matches"() {
        given: 'a tweet with a hashtag section filled in (without geolocation and user mention)'
        when: 'trying to retrieve place from the tweet by cross referencing hash tag with a city index'
        then: "user's location (place) will be the matching city"
    }

    def "should try to find a place by cross referencing tweet's content with a city index if no user mention matches"() {
        given: 'a tweet without geolocation, user mention and hashtag sections'
        when: 'trying to retrieve place from the tweet by cross referencing tweet content with a city index'
        then: "user's location (place) will be the first matching city"
    }

}
