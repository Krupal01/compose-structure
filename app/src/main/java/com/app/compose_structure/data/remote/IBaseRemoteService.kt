package com.app.compose_structure.data.remote

import com.app.compose_structure.common.Result
import com.app.compose_structure.data.remote.model.ResLogin

interface IBaseRemoteService {

    suspend fun login(
        email: String,
        password: String
    ): Result<ResLogin>

}