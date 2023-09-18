package com.app.compose_structure.domain.repository

import com.app.compose_structure.common.Result
import com.app.compose_structure.data.remote.model.ResLogin

interface IUserRepository {

    suspend fun login(email: String, password: String): Result<ResLogin?>

    suspend fun isLogin(): Boolean

    /* suspend fun storeUserSetting(userModel: UserDataModel)

     suspend fun getUserSetting(): UserDataModel*/

}