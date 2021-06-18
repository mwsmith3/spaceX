package com.mwsmith3.spacex.falcon9.data.network

import com.mwsmith3.spacex.falcon9.data.network.dto.LaunchDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface Falcon9LaunchesRestClient {

    @GET("v4/launches")
    fun get(): Single<List<LaunchDto>>
}
