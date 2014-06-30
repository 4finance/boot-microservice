package com.ofg.config.discovery
import com.ofg.infrastructure.discovery.ServiceConfigurationResolver
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.test.TestingServer
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder
import org.apache.curator.x.discovery.ServiceInstance
import org.apache.curator.x.discovery.UriSpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
@PackageScope
class ServiceDiscoveryTestingServerConfiguration {

    @Bean(destroyMethod = 'close')
    TestingServer testingServer(@Value('${service.resolver.url:localhost:2181}') String serviceResolverUrl,
                                ServiceConfigurationResolver serviceConfigurationResolver,
                                CuratorFramework curatorFramework,
                                @Value('${microservice.url:localhost}') String microserviceUrl,
                                @Value('${microservice.port:8080}') int microservicePort,
                                @Value('${microservice.context:rest}') String microserviceContext) {
        TestingServer testingServer = new TestingServer(serviceResolverUrl.split(':').last().toInteger())
        setupStubs(serviceConfigurationResolver, curatorFramework, microserviceUrl, microservicePort, microserviceContext)
        return testingServer
    }

    private void setupStubs(ServiceConfigurationResolver serviceConfigurationResolver,
                            CuratorFramework curatorFramework,
                            String microserviceUrl,
                            int microservicePort,
                            String microserviceContext) {
        serviceConfigurationResolver.dependencies.each {          
            ServiceInstance<Void> serviceInstance = ServiceInstance.builder().uriSpec(new UriSpec("{scheme}://{address}:{port}/$microserviceContext"))
                    .address(microserviceUrl)
                    .port(microservicePort)
                    .name(it.value)
                    .build()
            ServiceDiscoveryBuilder.builder(Void).basePath(serviceConfigurationResolver.basePath).client(curatorFramework).thisInstance(serviceInstance).build().start()
        }

    }

}
