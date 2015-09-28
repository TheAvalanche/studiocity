package org.symphodia.studiocity.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.model.Role
import org.symphodia.studiocity.model.User
import org.symphodia.studiocity.repository.UserRepository
import org.symphodia.studiocity.rest.dto.SignInRequest

import java.security.Principal

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    UserRepository userRepository

    @RequestMapping("/user")
    Principal user(Principal user) {
        user
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    void signIn(@RequestBody SignInRequest signInRequest) {
        userRepository.save(new User(email: signInRequest.email, password: new BCryptPasswordEncoder().encode(signInRequest.password), role: Role.USER)) //todo more serious implementation
    }

}
