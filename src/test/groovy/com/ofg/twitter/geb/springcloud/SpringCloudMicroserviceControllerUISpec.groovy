package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.MicroserviceControllerUISpec
import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(properties = "spring.profiles.active:dev,springCloud", webEnvironment = DEFINED_PORT)
class SpringCloudMicroserviceControllerUISpec extends MicroserviceControllerUISpec {

    @Override
    protected inputJson() {
        return [
                pl:
                        [this:'com/ofg/twitter-places-analyzer',
                        dependencies:
                                 [collerator:[path:'/com/ofg/twitter-places-collerator',
                                              stubs:'com.ofg:twitter-places-collerator:stubs',
                                              'load-balancer':'ROUND_ROBIN',
                                              required:false,
                                              contentTypeTemplate:'',
                                              headers:[:],
                                              version:'']
                                 ],
                         ]
        ]
    }
}
