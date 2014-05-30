package com.ofg.infrastructure.ports

import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import jetcd.EtcdClient
import jetcd.EtcdClientFactory
import org.springframework.core.env.PropertySource

@TypeChecked
@PackageScope
@Slf4j
class EtcdPropertySource extends PropertySource<EtcdClient> {

    @PackageScope static final String PROPERTY_SOURCE_NAME = 'Etcd'
    
    private static final String NON_SPRING_PARAMS_PREFIX = 'remote'
    
    private final String etcdPropertyRootPath
    
    EtcdPropertySource(String etcdPropertyRootPath, String etcdServerUrl) {
        super(PROPERTY_SOURCE_NAME, EtcdClientFactory.newInstance(etcdServerUrl))
        this.etcdPropertyRootPath = etcdPropertyRootPath
    }       

    @Override
    Object getProperty(String name) {
        if(propertyNameNotToBeVerified(name)) {
            return null
        }
        try {            
            return getSource().get(name)
        } catch (Exception exception) {
            log.warn("Exception [$exception] occurred while trying to retrieve property [$name]")
            return null
        }
    }

    private boolean propertyNameNotToBeVerified(String name) {
        return !name.startsWith("${NON_SPRING_PARAMS_PREFIX}.$etcdPropertyRootPath") || name.contains(":")
    }
    
}
