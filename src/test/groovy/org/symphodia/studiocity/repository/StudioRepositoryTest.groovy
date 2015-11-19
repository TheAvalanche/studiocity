package org.symphodia.studiocity.repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.symphodia.studiocity.Application
import org.symphodia.studiocity.model.Studio
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

    def "test studio repository CRUD methods"() {
        setup:
        studioRepository.save(new Studio(name: "Test Studio"))
        when:
        def count = studioRepository.count()
        def studio = studioRepository.findAll()[0]
        then:
        count == 1
        studio.name == "Test Studio"
    }

    def "test studio distinct values"() {
        setup:
        studioRepository.save([new Studio(city: "Test1"), new Studio(city: "Test1"), new Studio(city: "Test2")])
        when:
        def count = studioRepository.count()
        def cities = studioRepository.distinctCities()
        then:
        count == 3
        cities == ['Test1', 'Test2']

    }

}
