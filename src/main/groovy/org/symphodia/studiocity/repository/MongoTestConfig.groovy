package org.symphodia.studiocity.repository

import com.mongodb.Mongo
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("test")
@Configuration
class MongoTestConfig {

    @Bean
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version("3.0.6")
                .bindIp("127.0.0.1")
                .port(12345)
                .build();
    }

}
