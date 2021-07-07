package com.mwsmith3.spacex.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface AppSchedulers {
    val main: Scheduler
    val io: Scheduler
}