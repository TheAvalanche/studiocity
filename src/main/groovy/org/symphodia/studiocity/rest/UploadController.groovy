package org.symphodia.studiocity.rest

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.symphodia.studiocity.rest.dto.UploadResponse

@RestController
@RequestMapping("upload")
class UploadController {

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    ResponseEntity uploadImage(@RequestParam("file") MultipartFile request) {
        def name = generateFileName() + '.' + request.getOriginalFilename().tokenize('.').last()
        new File(name).withOutputStream {it << request.inputStream}
        new ResponseEntity<>(new UploadResponse(name: name), HttpStatus.OK)
    }

    public String generateFileName() {
        RandomStringUtils.randomAlphanumeric(32)
    }
}
