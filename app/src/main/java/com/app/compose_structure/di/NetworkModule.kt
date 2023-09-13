package com.app.compose_structure.di

import com.app.compose_structure.common.PreferenceKey
import com.app.compose_structure.common.interceptor.AuthInterceptor
import com.app.compose_structure.data.local.ISharedPreferences
import com.app.compose_structure.data.remote.ApiService
import com.app.compose_structure.model.UserDataModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
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
            .addInterceptor(AuthInterceptor(sharedPreferences))
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
            .baseUrl(runBlocking {
                val gson = Gson()
                gson.fromJson(
                    sharedPreferences.getValue(
                        PreferenceKey.KEY_USER_SETTING, gson.toJson(
                            UserDataModel()
                        )
                    ), UserDataModel::class.java
                ).settingWebUrl
            })
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideBaseRemoteService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}