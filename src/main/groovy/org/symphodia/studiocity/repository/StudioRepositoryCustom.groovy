package org.symphodia.studiocity.repository

import org.symphodia.studiocity.model.Studio
import org.symphodia.studiocity.model.StudioType


interface StudioRepositoryCustom {
    List<String> distinctCities()
    List<Studio> findByStudioTypesAndCityOptional(StudioType studioType, String city)
}
