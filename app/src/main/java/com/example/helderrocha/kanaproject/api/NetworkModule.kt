package com.example.helderrocha.kanaproject.api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module()
class NetworkModule {
    @Provides
    @Singleton
    fun providesGitApi(): ApiClient {

        val gitApi = Retrofit.Builder()
                .baseUrl(GitApi.URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GitApi::class.java)
        return ApiClient(gitApi)
    }
}