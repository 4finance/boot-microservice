package com.ofg.infrastructure.discovery
import com.ofg.base.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class ServiceResolverSpec extends IntegrationSpec {
    
    @Autowired ServiceResolver serviceResolver
    
    @Autowired DependencyWatcher watcher
    
    def "should resolve urls properly"() {
        expect:
            serviceResolver.getUrl("payments") == Optional.of('http://localhost:8080/rest')
    }
   
}
