package org.symphodia.studiocity.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
class Studio {

    @Id
    String id
    String name
    String logoUrl
    String city
    String street
    String house
    String index
    String email
    String phone
    String site
    String workingHours
    String description
    List<StudioType> studioTypes = new ArrayList<>()
}