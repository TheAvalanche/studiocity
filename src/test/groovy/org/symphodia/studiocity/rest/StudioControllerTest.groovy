package org.symphodia.studiocity.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.symphodia.studiocity.Application
import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.User
import org.symphodia.studiocity.repository.StudioRepository
import org.symphodia.studiocity.repository.UserRepository
import org.symphodia.studiocity.security.SecurityUser
import spock.lang.Specification

@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application)
@WebAppConfiguration
@IntegrationTest("server.port:9999")
class StudioControllerTest extends Specification {

    @Autowired
    StudioController studioController

    @Autowired
    StudioRepository studioRepository

    @Autowired
    UserRepository userRepository

    User sharedUser

    def setup() {
        sharedUser = userRepository.save(new User(email: "test@test.com", password: "pass"))
    }

    def "test save and update studio"() {
        when: "saving studio"
        studioController.save(new SecurityUser(sharedUser), new Studio(name: "Test 1"))
        def studio = studioRepository.findOneByName("Test 1")
        def dbUser = userRepository.findOneByEmail("test@test.com")
        then:
        studio.name == "Test 1"
        dbUser.studios[0].name == "Test 1"

        when: "updating studio name"
        studio.name = "Test 2"
        studioController.save(new SecurityUser(sharedUser), studio)
        def updatedStudio = studioRepository.findOneByName("Test 2")
        def updatedDbUser = userRepository.findOneByEmail("test@test.com")
        then:
        updatedStudio.name == "Test 2"
        updatedDbUser.studios[0].name == "Test 2"
    }

}
