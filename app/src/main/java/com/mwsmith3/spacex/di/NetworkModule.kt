package com.mwsmith3.spacex.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mwsmith3.spacex.falcon9.data.network.Falcon9LaunchesRestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideFalcon9LaunchesService(retrofit: Retrofit): Falcon9LaunchesRestClient {
        return retrofit.create(Falcon9LaunchesRestClient::class.java)
    }
}
