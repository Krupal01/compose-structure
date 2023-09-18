package com.app.compose_structure.data.remote

import com.app.compose_structure.common.Result
import com.app.compose_structure.data.remote.model.ResLogin
import com.app.compose_structure.data.remote.model.ResUserList
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface IBaseRemoteService {

    suspend fun login(
        email: String,
        password: String
    ): Result<ResLogin>

    suspend fun getUserList(
        page : Int
    ): Result<ResUserList?>

}