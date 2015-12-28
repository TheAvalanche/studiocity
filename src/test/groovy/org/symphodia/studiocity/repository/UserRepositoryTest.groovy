package org.symphodia.studiocity.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.symphodia.studiocity.Application
import org.symphodia.studiocity.model.User
import spock.lang.Specification

@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application)
@WebAppConfiguration
@IntegrationTest("server.port:9999")
class UserRepositoryTest extends Specification {

    @Autowired
    UserRepository userRepository

    def setup() {
        userRepository.save([new User(email: "test1@test.com"),
                             new User(email: "test2@test.com"),
                             new User(email: "test3@test.com")])
    }

    def cleanup() {
        userRepository.deleteAll()
    }

    def "test find user by email"() {
        when:
        def count = userRepository.count()
        def user = userRepository.findOneByEmail("test1@test.com")
        then:
        count == 3L
        user.email == "test1@test.com"

    }
}
