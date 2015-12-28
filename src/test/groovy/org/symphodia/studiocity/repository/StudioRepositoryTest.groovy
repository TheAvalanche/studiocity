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
        count == 3L
        studio.name == "Test Studio 1"
    }

    def "test studio distinct values"() {
        when:
        def count = studioRepository.count()
        def cities = studioRepository.distinctCities()
        then:
        count == 3L
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

    def "test count studio by studio type and city"() {
        expect:
        studioRepository.countByStudioTypesAndCityOptional(studioType, city) == expectedCount
        where:
        studioType              | city      | expectedCount
        StudioType.RECORDING    | "Test1"   | 1L
        StudioType.RECORDING    | "Test2"   | 1L
        StudioType.RECORDING    | null      | 2L
        StudioType.REHEARSAL    | "Test1"   | 0L
        StudioType.REHEARSAL    | "Test2"   | 2L
        StudioType.REHEARSAL    | null      | 2L
        null                    | "Test1"   | 1L
        null                    | "Test2"   | 2L
        null                    | null      | 3L

    }

    def "test studio pagination"() {
        setup:
        studioRepository.deleteAll()
        studioRepository.save([new Studio(name: "Test Studio 1"),
                               new Studio(name: "Test Studio 2"),
                               new Studio(name: "Test Studio 3"),
                               new Studio(name: "Test Studio 4"),
                               new Studio(name: "Test Studio 5"),
                               new Studio(name: "Test Studio 6"),
                               new Studio(name: "Test Studio 7"),
                               new Studio(name: "Test Studio 8"),
                               new Studio(name: "Test Studio 9"),
                               new Studio(name: "Test Studio 10"),
                               new Studio(name: "Test Studio 11"),
                               new Studio(name: "Test Studio 12")])
        when:
        def totalCount = studioRepository.count()
        def defaultPaginationCount = studioRepository.findByStudioTypesAndCityOptional(null, null).size()
        def skip3limit3 = studioRepository.findByStudioTypesAndCityOptional(null, null, 3, 3)
        then:
        totalCount == 12L
        defaultPaginationCount == 10
        skip3limit3.size() == 3
        skip3limit3[0].name == "Test Studio 4"
        skip3limit3[1].name == "Test Studio 5"
        skip3limit3[2].name == "Test Studio 6"
    }

}
