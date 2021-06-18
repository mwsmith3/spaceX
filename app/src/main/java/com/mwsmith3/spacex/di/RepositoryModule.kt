package com.mwsmith3.spacex.di

import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.data.Falcon9RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindFalcon9Repository(impl: Falcon9RepositoryImpl): Falcon9Repository
}
