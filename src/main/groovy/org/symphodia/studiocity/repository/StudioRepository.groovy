package org.symphodia.studiocity.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.symphodia.studiocity.model.Studio


interface StudioRepository extends MongoRepository<Studio, String>, StudioRepositoryCustom {
}
