package com.app.compose_structure.domain.repository

import com.app.compose_structure.common.PreferenceKey
import com.app.compose_structure.common.Result
import com.app.compose_structure.data.local.ISharedPreferences
import com.app.compose_structure.data.remote.IBaseRemoteService
import com.app.compose_structure.data.remote.model.ResLogin
import com.app.compose_structure.data.remote.model.ResUserList
import com.app.compose_structure.di.IoDispatcher
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: ISharedPreferences,
    private val remoteDataSource: IBaseRemoteService,
) : IUserRepository {

    private val gson: Gson = Gson()

    /*override suspend fun storeUserSetting(userModel: UserDataModel) {
        sharedPreferences.storeValue(PreferenceKey.KEY_USER_SETTING, gson.toJson(userModel))
    }

    override suspend fun getUserSetting(): UserDataModel {
        val data =
            sharedPreferences.getValue(PreferenceKey.KEY_USER_SETTING, gson.toJson(UserDataModel()))
        return gson.fromJson(data, UserDataModel::class.java)
    }*/

    override suspend fun login(email: String, password: String): Result<ResLogin?> =
        withContext(dispatcher) {
            remoteDataSource.login(email = email, password = password).apply {
                if (this is Result.Success) {
                    sharedPreferences.storeValue(PreferenceKey.TOKEN, this.data.token ?: "")
                }
            }
        }

    override suspend fun isLogin(): Boolean {
        val data =
            sharedPreferences.getValue(PreferenceKey.TOKEN, "")
        return data.isNotEmpty()
    }

    override suspend fun getUserList(page: Int): Result<ResUserList?> = withContext(dispatcher) {
        remoteDataSource.getUserList(page)
    }
}