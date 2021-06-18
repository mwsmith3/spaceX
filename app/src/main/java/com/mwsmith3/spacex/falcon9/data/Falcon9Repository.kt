package com.mwsmith3.spacex.falcon9.data

import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import io.reactivex.rxjava3.core.Single

interface Falcon9Repository {
    fun getFalcon9Launches(): Single<List<Falcon9Launch>>
}
