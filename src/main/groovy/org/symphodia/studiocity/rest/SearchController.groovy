package org.symphodia.studiocity.rest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.model.StudioType
import org.symphodia.studiocity.repository.StudioRepository

@RestController
@RequestMapping("rest")
class SearchController {

    @Autowired
    StudioRepository studioRepository

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    def search(@RequestParam(required = false) StudioType studioType,
               @RequestParam(required = false) String city,
               @RequestParam(required = false) Integer skip,
               @RequestParam(required = false) Integer limit) {
        studioRepository.findByStudioTypesAndCityOptional(studioType, city, skip ?: 0, limit ?: 10)
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    def count(@RequestParam(required = false) StudioType studioType,
              @RequestParam(required = false) String city) {
        studioRepository.countByStudioTypesAndCityOptional(studioType, city)
    }

    @RequestMapping("/cities")
    List<String> cities() {
        studioRepository.distinctCities()
    }

    @RequestMapping("/studioTypes")
    List<StudioType> studioTypes() {
        StudioType.values()
    }

}
