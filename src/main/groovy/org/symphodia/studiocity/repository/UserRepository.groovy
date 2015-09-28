package org.symphodia.studiocity.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.symphodia.studiocity.model.User

interface UserRepository extends MongoRepository<User, String> {

    User findOneByEmail(String email);

}