package org.wsd.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.wsd.core.eventstore.EventStoreRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = {EventStoreRepository.class})
public class MongoConfig {

}
