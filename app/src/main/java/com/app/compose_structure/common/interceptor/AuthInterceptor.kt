package com.app.compose_structure.common.interceptor

import com.app.compose_structure.common.APPLICATION_JSON
import com.app.compose_structure.common.AUTHORIZATION
import com.app.compose_structure.common.CONTENT_TYPE
import com.app.compose_structure.common.PreferenceKey
import com.app.compose_structure.common.extentions.toBase64Encode
import com.app.compose_structure.data.local.ISharedPreferences
import com.app.compose_structure.model.UserDataModel
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val sharedPreferences: ISharedPreferences) : Interceptor {

    private val gson = Gson()

    private var defaultObj: String = gson.toJson(UserDataModel())

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        val userSetting = runBlocking {
            gson.fromJson(
                sharedPreferences.getValue(
                    PreferenceKey.KEY_USER_SETTING,
                    defaultObj
                ), UserDataModel::class.java
            )
        }

        val token = userSetting.settingUsername

        var httpNewUrl: HttpUrl? = null

        val newUrl = try {
            val url: String = userSetting.settingWebUrl

            httpNewUrl = url.toHttpUrl()

            httpNewUrl.let { newUrl ->
                chain.request().url.newBuilder().apply {
                    host(newUrl.host)
                    port(newUrl.port)
                    if (newUrl.pathSegments.isNotEmpty()) {
                        setPathSegment(0, newUrl.pathSegments.first())
                    }
                }.build()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        val request = if (!chain.request().url.toString()
                .startsWith(httpNewUrl.toString()) && newUrl != null
        ) {
            chain.request().newBuilder()
                .url(newUrl)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, "Basic ${token.toBase64Encode()}")
                .build()
        } else {
            chain.request().newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, "Basic ${token.toBase64Encode()}")
                .build()
        }
        return chain.proceed(request)
    }
}