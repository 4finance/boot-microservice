package com.ofg.infrastructure.discovery

import org.apache.curator.framework.CuratorFramework

class ServiceResolver {
    
    private final CuratorFramework curatorFramework
    private final ServiceConfigurationResolver serviceConfigurationResolver

    ServiceResolver(CuratorFramework curatorFramework, ServiceConfigurationResolver serviceConfigurationResolver) {
        this.curatorFramework = curatorFramework
        this.serviceConfigurationResolver = serviceConfigurationResolver
    }

    String getUrl(String dependency) {
        def parsedConfiguration = serviceConfigurationResolver.parseConfiguration()    
    }
    
}
