package com.mwsmith3.spacex.falcon9.di

import com.mwsmith3.spacex.falcon9.data.FakeFalcon9Repository
import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [Falcon9Module::class]
)
abstract class FakeFalcon9Module {
    @Singleton
    @Binds
    abstract fun bindFalcon9Repository(
        fake: FakeFalcon9Repository
    ): Falcon9Repository
}
