package org.symphodia.studiocity.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class AuthController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user
    }

}