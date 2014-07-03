package com.ofg.infrastructure.discovery
import com.ofg.base.IntegrationSpec
import com.ofg.infrastructure.discovery.watcher.DependencyWatcher
import org.springframework.beans.factory.annotation.Autowired
import com.google.common.base.Optional

class ServiceResolverSpec extends IntegrationSpec {    
    
    @Autowired ServiceResolver serviceResolver
    
    @Autowired DependencyWatcher watcher        
    
    def 'should resolve urls properly'() {
        expect:
            serviceResolver.getUrl("trustly") == Optional.of('http://localhost:8080/rest')
    }
   
}
