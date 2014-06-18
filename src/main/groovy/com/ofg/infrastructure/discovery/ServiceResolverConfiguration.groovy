package com.ofg.infrastructure.discovery
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.RetryNTimes
import org.apache.curator.x.discovery.ServiceDiscovery
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder
import org.apache.curator.x.discovery.ServiceInstance
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@TypeChecked
@Configuration
class ServiceResolverConfiguration {
        
    
    @PackageScope
    @Bean(initMethod = 'start', destroyMethod = 'close')
    CuratorFramework curatorFramework(@Value('${service.resolver.url:http://localhost:2181}') String serviceResolverUrl,
                                      @Value('${service.resolver.connection.retries:5}') int numberOfRetries,
                                      @Value('${service.resolver.connection.timeout:1000}') int timeout) {
        return CuratorFrameworkFactory.newClient(serviceResolverUrl, new RetryNTimes(numberOfRetries, timeout))
    }

    // per dependency
//    ServiceCache serviceCache = client.serviceCacheBuilder().name('sth').build()
    
    
    @PackageScope
    @Bean(initMethod = 'start', destroyMethod = 'close')
    ServiceInstance serviceInstance(@Value('${microservice.url:localhost}') String microserviceUrl,
                                    @Value('${microservice.port:8080}') int microservicePort,
                                    ServiceConfigurationResolver serviceConfigurationResolver) {
        return ServiceInstance.builder().address(microserviceUrl)
                                        .port(microservicePort)
                                        .name(serviceConfigurationResolver.microserviceName)
                                        .build()
    }
    
    @PackageScope
    @Bean(initMethod = 'start', destroyMethod = 'close')
    ServiceDiscovery serviceDiscovery(CuratorFramework curatorFramework, 
                                      ServiceInstance serviceInstance,
                                      ServiceConfigurationResolver serviceConfigurationResolver) {
        return ServiceDiscoveryBuilder.builder(Void).basePath(serviceConfigurationResolver.basePath).client(curatorFramework).thisInstance(serviceInstance).build()
    }
    
    

    @Bean
    ServiceResolver serviceResolver(CuratorFramework curatorFramework, @Value('${microservice.config.file:microservice.json}') Resource microserviceConfig) {
        ServiceConfigurationResolver serviceConfigurationResolver = new ServiceConfigurationResolver(microserviceConfig.file.text)
        return new ServiceResolver(curatorFramework, serviceConfigurationResolver)
    }
    
}
