package com.app.compose_structure.di

import com.app.compose_structure.common.HOST_URL
import com.app.compose_structure.common.interceptor.AuthInterceptor
import com.app.compose_structure.data.local.ISharedPreferences
import com.app.compose_structure.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(sharedPreferences: ISharedPreferences): OkHttpClient =
        OkHttpClient().newBuilder()
            /*.addInterceptor(AuthInterceptor(sharedPreferences))*/
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        sharedPreferences: ISharedPreferences
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(HOST_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideBaseRemoteService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}