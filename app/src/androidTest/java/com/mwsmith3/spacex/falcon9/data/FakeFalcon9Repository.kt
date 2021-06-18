package com.mwsmith3.spacex.falcon9.data

import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FakeFalcon9Repository @Inject constructor() : Falcon9Repository {

    var response: Single<List<Falcon9Launch>>? = null

    override fun getFalcon9Launches(): Single<List<Falcon9Launch>> {
        return response ?: throw Exception("response not set in $this")
    }
}
