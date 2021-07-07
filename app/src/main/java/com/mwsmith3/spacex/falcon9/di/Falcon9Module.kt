package com.mwsmith3.spacex.falcon9.di

import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.data.Falcon9RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class Falcon9Module {

    @Binds
    abstract fun bindFalcon9Repository(impl: Falcon9RepositoryImpl): Falcon9Repository
}
