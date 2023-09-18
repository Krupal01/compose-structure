package com.app.compose_structure.data.remote

import com.app.compose_structure.data.remote.model.ResLogin
import com.app.compose_structure.data.remote.model.ResUserList
import com.app.compose_structure.data.remote.request.ReqLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body body: ReqLogin
    ): Response<ResLogin?>

    @GET("users")
    suspend fun getUserList(
        @Query("page") page : Int
    ): Response<ResUserList?>
}