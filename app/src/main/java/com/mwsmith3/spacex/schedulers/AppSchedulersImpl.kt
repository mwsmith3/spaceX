package com.mwsmith3.spacex.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AppSchedulersImpl @Inject constructor() : AppSchedulers {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()

    override val io: Scheduler
        get() = Schedulers.io()
}
