package org.symphodia.studiocity.repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate

class StudioRepositoryImpl implements StudioRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate

    List<String> distinctCities() {
        mongoTemplate.getCollection("studio").distinct("city")
    }
}
