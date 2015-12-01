package org.symphodia.studiocity.repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.StudioType

class StudioRepositoryImpl implements StudioRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate

    List<String> distinctCities() {
        mongoTemplate.getCollection("studio").distinct("city")
    }

    List<Studio> findByStudioTypesAndCityOptional(StudioType studioType, String city) {
        List<Criteria> criterias = new ArrayList<>()

        if (studioType) {
            criterias.add(Criteria.where("studioTypes").is(studioType))
        }
        if (city) {
            criterias.add(Criteria.where("city").is(city))
        }

        Query query = new Query()
        if (!criterias.empty) {
            query.addCriteria(new Criteria().andOperator(criterias as Criteria[]))
        }
        mongoTemplate.find(query, Studio.class)
    }
}
