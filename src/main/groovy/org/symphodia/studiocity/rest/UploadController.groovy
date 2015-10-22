package org.symphodia.studiocity.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("upload")
class UploadController {

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    ResponseEntity checkChunk(def image) {
        println image
        new ResponseEntity<>(HttpStatus.NO_CONTENT)
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    void uploadChunk(def image) {
        println("POST " + image)

    }

}
