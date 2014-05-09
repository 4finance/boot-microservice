package com.ofg.config

import com.mongodb.Mongo
import com.mongodb.WriteConcern
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoFactoryBean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.WriteResultChecking

@TypeChecked
@Configuration
class DbConfiguration {
        
    @Bean 
    MongoFactoryBean mongo(@Value('${mongodb.host:localhost}') String host, @Value('${mongodb.port:27017}') Integer port) {
        MongoFactoryBean mongo = new MongoFactoryBean()
        mongo.setHost(host)
        mongo.setPort(port)
        mongo.setWriteConcern(WriteConcern.FSYNCED)
        return mongo
    }

    @Bean 
    MongoDbFactory mongoDbFactory(Mongo mongo, @Value('${mongodb.dbname:trustly}') String dbName) {
        return new SimpleMongoDbFactory(mongo, dbName)
    }

    @Bean 
    MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory)
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION)
        return mongoTemplate
    }    
    
}
