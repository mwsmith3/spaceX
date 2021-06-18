package com.mwsmith3.spacex.falcon9.data

import com.mwsmith3.spacex.falcon9.data.network.Falcon9LaunchesRestClient
import com.mwsmith3.spacex.falcon9.data.network.dto.LaunchDtoMapper
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Falcon9RepositoryImpl @Inject constructor(
    private val restClient: Falcon9LaunchesRestClient,
    private val mapper: LaunchDtoMapper
) : Falcon9Repository {

    override fun getFalcon9Launches(): Single<List<Falcon9Launch>> {
        return restClient.get().map { mapper.map(it) }
    }
}
