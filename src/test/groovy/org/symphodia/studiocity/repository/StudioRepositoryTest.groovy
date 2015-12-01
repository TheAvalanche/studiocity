package org.symphodia.studiocity.repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.symphodia.studiocity.Application
import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.StudioType
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

    def setup() {
        studioRepository.save([new Studio(name: "Test Studio 1", studioTypes: [StudioType.RECORDING], city: "Test1"),
                               new Studio(name: "Test Studio 2", studioTypes: [StudioType.RECORDING, StudioType.REHEARSAL], city: "Test2"),
                               new Studio(name: "Test Studio 3", studioTypes: [StudioType.REHEARSAL], city: "Test2")])
    }

    def cleanup() {
        studioRepository.deleteAll()
    }

    def "test studio repository CRUD methods"() {
        when:
        def count = studioRepository.count()
        def studio = studioRepository.findAll()[0]
        then:
        count == 3
        studio.name == "Test Studio 1"
    }

    def "test studio distinct values"() {
        when:
        def count = studioRepository.count()
        def cities = studioRepository.distinctCities()
        then:
        count == 3
        cities == ['Test1', 'Test2']

    }

    def "test find studio by studio type and city"() {
        expect:
        studioRepository.findByStudioTypesAndCityOptional(studioType, city).size == expectedSize
        where:
        studioType              | city      | expectedSize
        StudioType.RECORDING    | "Test1"   | 1
        StudioType.RECORDING    | "Test2"   | 1
        StudioType.RECORDING    | null      | 2
        StudioType.REHEARSAL    | "Test1"   | 0
        StudioType.REHEARSAL    | "Test2"   | 2
        StudioType.REHEARSAL    | null      | 2
        null                    | "Test1"   | 1
        null                    | "Test2"   | 2
        null                    | null      | 3

    }

}
