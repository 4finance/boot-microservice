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
    @Bean(initMethod = 'registerDependencies', destroyMethod = 'unregisterDependencies')
    DependencyWatcher dependencyWatcher(ServiceConfigurationResolver serviceConfigurationResolver, ServiceDiscovery serviceDiscovery) {
        return new DependencyWatcher(serviceConfigurationResolver.dependencies, serviceDiscovery)
    }
    
    @PackageScope
    @Bean
    ServiceConfigurationResolver serviceConfigurationResolver(@Value('${microservice.config.file:microservice.json}') Resource microserviceConfig) {
        return new ServiceConfigurationResolver(microserviceConfig.file.text)
    }
    
    
    @PackageScope
    @Bean(initMethod = 'start', destroyMethod = 'close')
    CuratorFramework curatorFramework(@Value('${service.resolver.url:http://localhost:2181}') String serviceResolverUrl,
                                      @Value('${service.resolver.connection.retries:5}') int numberOfRetries,
                                      @Value('${service.resolver.connection.timeout:1000}') int timeout) {
        return CuratorFrameworkFactory.newClient(serviceResolverUrl, new RetryNTimes(numberOfRetries, timeout))
    }    
    
    
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
    ServiceResolver serviceResolver(ServiceConfigurationResolver serviceConfigurationResolver, ServiceDiscovery serviceDiscovery) {
        return new ServiceResolver(serviceConfigurationResolver, serviceDiscovery)
    }
    
}
