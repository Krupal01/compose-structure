package com.app.compose_structure.data.remote

import com.app.compose_structure.data.remote.model.ResLogin
import com.app.compose_structure.data.remote.request.ReqLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body body: ReqLogin
    ): Response<ResLogin?>
}