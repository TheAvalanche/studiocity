package org.symphodia.studiocity.rest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.User
import org.symphodia.studiocity.repository.StudioRepository
import org.symphodia.studiocity.repository.UserRepository
import org.symphodia.studiocity.security.SecurityUser

@RestController
@RequestMapping("studio")
class StudioController {

    @Autowired
    StudioRepository studioRepository

    @Autowired
    UserRepository userRepository

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void save(@AuthenticationPrincipal SecurityUser principal, @RequestBody Studio studio) {
        User savedUser = principal.user
        Studio savedStudio = studioRepository.save(studio)

        savedUser.studios << savedStudio
        userRepository.save(savedUser)
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    void remove(@AuthenticationPrincipal SecurityUser principal, @RequestBody Studio studio) {
        User savedUser = principal.user

        savedUser.studios.remove(studio)
        userRepository.save(savedUser)

        studioRepository.delete(studio)
    }


    @RequestMapping(value = "/findByCurrentUser")
    List<Studio> findByCurrentUser(@AuthenticationPrincipal SecurityUser principal) {
        User savedUser = principal.user

        savedUser.studios
    }


}
