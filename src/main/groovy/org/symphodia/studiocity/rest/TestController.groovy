package org.symphodia.studiocity.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @RequestMapping("/test")
    Map test() {
        return  [id: UUID.randomUUID().toString(), content: "Hello World!"]
    }

}
