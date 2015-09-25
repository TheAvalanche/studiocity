package org.symphodia.studiocity.repository
import com.mongodb.Mongo
import com.mongodb.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.config.AbstractMongoConfiguration

@Profile("prod")
@Configuration
//@EnableMongoRepositories
class MongoConfig extends AbstractMongoConfiguration {

    String getDatabaseName() {
        "StudioCityDB"
    }

    Mongo mongo() {
        new MongoClient()
    }
}
