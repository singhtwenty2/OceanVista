package com.singhtwenty2.oceanvista.feature_home.data.mapper

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachDetail
import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachDetailResponseDTO
import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachResponseDTO
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachDetailDomainModel
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.FullBeachDetailDomainModel

fun BeachResponseDTO.toBeach(): Beach {
    return Beach(
        id = id.toString(),
        name = name,
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        region = region,
        openingTime = openingTime,
        closingTime = closingTime,
        activities = activities,
        photos = photos
    )
}

fun BeachDetailResponseDTO.toDomainModel(): FullBeachDetailDomainModel {
    return FullBeachDetailDomainModel(
        id = this.id,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        region = this.region,
        description = this.description,
        beachDetail = this.beachDetail.toDomainModel(),
        photos = this.photos
    )
}

fun BeachDetail.toDomainModel(): BeachDetailDomainModel {
    return BeachDetailDomainModel(
        activities = this.activities,
        amenities = this.amenities,
        openingTime = this.openingTime,
        closingTime = this.closingTime,
        averageCrowdCount = "$averageCrowdCount people",
        peakTimes = this.peakTimes,
        facilities = this.facilities,
        services = this.services,
        accessibleForDisabled = this.accessibleForDisabled,
        weatherForecast = this.weatherForecast,
        petFriendly = this.petFriendly,
        foodAndBeverageOptions = this.foodAndBeverageOptions,
        shadeAvailable = this.shadeAvailable,
        parkingInfo = this.parkingInfo,
        surfConditions = this.surfConditions,
        lifeguardsAvailable = this.lifeguardsAvailable,
        familyFriendly = this.familyFriendly,
        wifiAvailable = this.wifiAvailable,
        beachType = this.beachType,
        rulesAndRegulations = this.rulesAndRegulations,
        emergencyContactInfo = this.emergencyContactInfo
    )
}