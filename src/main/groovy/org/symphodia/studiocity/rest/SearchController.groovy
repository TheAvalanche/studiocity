package org.symphodia.studiocity.rest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.repository.StudioRepository

@RestController
@RequestMapping("rest")
class SearchController {

    @Autowired
    StudioRepository studioRepository

    @RequestMapping("/search")
    def search() {
        studioRepository.findAll()
    }

    @RequestMapping("/count")
    def count() {
        studioRepository.count()
    }

    @RequestMapping("/cities")
    List<String> cities() {
        studioRepository.distinctCities()
    }

}
