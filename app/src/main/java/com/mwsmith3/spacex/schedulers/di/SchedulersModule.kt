package com.mwsmith3.spacex.schedulers.di

import com.mwsmith3.spacex.schedulers.AppSchedulers
import com.mwsmith3.spacex.schedulers.AppSchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SchedulersModule {

    @Singleton
    @Binds
    abstract fun bindAppSchedulers(impl: AppSchedulersImpl): AppSchedulers
}
