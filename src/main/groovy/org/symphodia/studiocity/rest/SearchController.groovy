package org.symphodia.studiocity.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.symphodia.studiocity.model.Studio

@RestController
class SearchController {

    @RequestMapping("/search")
    List<Studio> search() {
        [new Studio(
             id: 1,
             name: "Studio 1",
             logoUrl: "http://lorempixel.com/400/300/technics/1",
             city: "Riga",
             street: "Mukusalas iela",
             house: "15",
             email: "test@test.com",
             phone: "28272988",
             workingHours: "24/7",
             description: "The best studio ever in the city center",
             latitude: 15, longitude: -26
        ),new Studio(
             id: 2,
             name: "Studio 2",
             logoUrl: "http://lorempixel.com/400/300/technics/2",
             city: "Riga",
             street: "Mukusalas iela",
             house: "15",
             site: "http://www.studio2.lv",
             phone: "28272988",
             workingHours: "24/7",
             description: "The best studio ever in the city center",
             latitude: 0, longitude: -180
        ),new Studio(
             id: 3,
             name: "Studio 3",
             logoUrl: "http://lorempixel.com/400/300/technics/3",
             city: "Riga",
             street: "Mukusalas iela",
             house: "15",
             email: "test@test.com",
             site: "http://www.studio3.lv",
             phone: "28272988",
             workingHours: "24/7",
             description: "The best studio ever in the city center",
             latitude: 33, longitude: 2
        )]
    }

}
