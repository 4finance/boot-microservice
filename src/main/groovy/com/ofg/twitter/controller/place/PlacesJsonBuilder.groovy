package com.ofg.twitter.controller.place

import groovy.text.SimpleTemplateEngine
import groovy.transform.TypeChecked

@TypeChecked
class PlacesJsonBuilder {

    String buildPlacesJson(long pairId, Map<String, Optional<Place>> places) {
        return """[
                       ${places.collect { String tweetId, Optional<Place> place ->
                            
                            return new SimpleTemplateEngine().createTemplate(JSON_RESPONSE_TEMPLATE)
                            .make([pairId: pairId,
                                   tweetId : tweetId,
                                   place: place])
                            .toString()
                        }.join(',')}
                   ]""".toString()
    }


    private static final String JSON_RESPONSE_TEMPLATE = '''
                {
                    "pair_id" : $pairId,
                    "tweet_id" : "$tweetId"
                    <% if (place.present) { %> 
                        ,"place" :
                        {
                            "name":"${place.get().placeDetails.name}",
                            "country_code": "${place.get().placeDetails.countryCode}"
                        },
                        "probability" : "${place.get().placeResolutionProbability}",
                        "origin" : "${place.get().placeResolutionOrigin}"
                    <% } %>
                }
                '''

}
