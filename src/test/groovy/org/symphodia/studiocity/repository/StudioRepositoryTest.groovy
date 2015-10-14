package org.symphodia.studiocity.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.symphodia.studiocity.Application
import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.User
import spock.lang.Specification

@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application)
@WebAppConfiguration
@IntegrationTest("server.port:9999")
class StudioRepositoryTest extends Specification {

    @Autowired
    StudioRepository studioRepository

    @Autowired
    UserRepository userRepository

    def "test studio repository"() {
        setup:
        studioRepository.save(new Studio(name: "Test Studio"))
        when:
        def count = studioRepository.count()
        def studio = studioRepository.findAll()[0]
        then:
        count == 1
        studio.name == "Test Studio"
    }

    def "test user repository"() {
        setup:
        def savedUser = userRepository.save(new User(email: "test@test.com"))
        def savedStudio = studioRepository.save(new Studio(name: "Test Studio"))
        when:
        savedUser.studios << savedStudio
        userRepository.save(savedUser)
        def count = userRepository.count()
        def user = userRepository.findAll()[0]
        then:
        count == 1
        user.studios[0].name == "Test Studio"
    }
}
