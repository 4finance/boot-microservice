package com.ofg.infrastructure.ports

import com.ofg.config.Profiles
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MutablePropertySources

@TypeChecked
@Configuration
@Profile(Profiles.PRODUCTION)
class EtcdConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer etcdPropertySourcesPlaceholderConfigurer(ConfigurableEnvironment configurableEnvironment) {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer()
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources()
        String etcdRootPath = configurableEnvironment.getProperty('etcd.property.root.path', 'trustly')
        String etcdHost = configurableEnvironment.getProperty('etcd.host', 'http://127.0.0.1:4001')
        propertySources.addLast(new EtcdPropertySource(etcdRootPath, etcdHost))
        properties.setPropertySources(propertySources)
        return properties
    }      

}
