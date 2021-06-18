package com.mwsmith3.spacex.falcon9.data.network.dto

import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import dagger.Reusable
import org.joda.time.DateTime
import javax.inject.Inject

@Reusable
class LaunchDtoMapper @Inject constructor() {

    fun map(dto: List<LaunchDto>): List<Falcon9Launch> {
        return dto.map {
            Falcon9Launch(
                name = it.name,
                success = it.success,
                imageUrl = it.links.patch.small,
                launchDate = DateTime.parse(it.launchDate)
            )
        }
    }
}
