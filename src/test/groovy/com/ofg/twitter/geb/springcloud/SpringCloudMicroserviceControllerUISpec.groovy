package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.MicroserviceControllerUISpec
import org.springframework.boot.test.IntegrationTest
import spock.lang.Ignore

@IntegrationTest("spring.profiles.active:dev,springCloud")
@Ignore("Still there are some issues with ids of page components")
class SpringCloudMicroserviceControllerUISpec extends MicroserviceControllerUISpec {

    @Override
    protected inputJson() {
        return [
                pl:
                        [dependencies:
                                 [collerator:[contentTypeTemplate:'',
                                              headers:[:],
                                              'load-balancer':'ROUND_ROBIN',
                                              path:'com/ofg/twitter-places-collerator',
                                              required:false,
                                              stubs:'com.ofg:twitter-places-collerator:stubs',
                                              version:'']
                                 ],
                         this:'com/ofg/twitter-places-analyzer']
        ]
    }
}
