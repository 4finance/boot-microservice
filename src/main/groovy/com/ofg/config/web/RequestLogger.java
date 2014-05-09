package com.ofg.config.web;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface RequestLogger {
	default void logRequest(String requestFromTrustly, String collectionName) {
		getMongoTemplate().insert(requestFromTrustly, collectionName);
	}
	MongoTemplate getMongoTemplate();
}
