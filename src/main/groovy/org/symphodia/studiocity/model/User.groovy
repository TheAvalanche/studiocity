package org.symphodia.studiocity.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User {

    @Id
    String id
    String email
    String password
    Role role = Role.USER
    @DBRef
    List<Studio> studios
}
