package org.symphodia.studiocity.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.model.Studio

@RestController
@RequestMapping("studio")
class StudioController {

    @Autowired
    MongoRepository mongoRepository

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void save(@RequestBody Studio studio) {
        mongoRepository.save(studio)
    }
}
