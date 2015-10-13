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

    def "init test groovy"() {
        setup:
        studioRepository.save(new Studio(name: "Test Studio"))
        when:
        def count = studioRepository.count()
        def studio = studioRepository.findAll()[0]
        then:
        count == 1
        studio.name == "Test Studio"
    }

    def "init  groovy"() {
        setup:
        userRepository.save(new User())
        def saved = studioRepository.save(new Studio(name: "Test Studio"))
        def user = userRepository.findAll()[0]
        user.studios << saved
        when:
        def count = studioRepository.count()
        def studio = studioRepository.findAll()[0]
        then:
        count == 1
        studio.name == "Test Studio"
    }
}
