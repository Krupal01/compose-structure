package com.app.compose_structure.data.remote

import com.app.compose_structure.R
import com.app.compose_structure.common.CustomException
import com.app.compose_structure.common.Result
import com.app.compose_structure.data.remote.model.ApiErrorModel
import com.app.compose_structure.data.remote.model.BaseApiErrorModel
import com.app.compose_structure.data.remote.model.ResLogin
import com.app.compose_structure.data.remote.model.ResUserList
import com.app.compose_structure.data.remote.request.ReqLogin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteServiceImpl @Inject constructor(
    private val api: ApiService
) : IBaseRemoteService {

    private suspend fun <T : Any> apiRequest(apiFunc: suspend () -> Response<T?>): Result<T?> =
        try {
            parseNetworkData(apiFunc.invoke())
        } catch (exception: Exception) {
            Result.Error(
                exception = if (exception is UnknownHostException || exception is ConnectException) {
                    CustomException(
                        messageResId = R.string.please_check_your_internet_connection
                    )
                } else {
                    exception
                }
            )
        }

    private fun <T> parseNetworkData(networkResponse: Response<T>): Result<T?> {
        return if (networkResponse.isSuccessful) {
            Result.Success(networkResponse.body())
        } else {
            if (networkResponse.code() == 401) {
                val errorBody = networkResponse.errorBody()?.string()
                val errorResponse = Gson().fromJson<ApiErrorModel>(
                    errorBody,
                    ApiErrorModel::class.java
                )
                throw CustomException(message = errorResponse.statusMessage ?: "")
            } else {
                val errorBody = networkResponse.errorBody()?.string()
                val errorResponse = Gson().fromJson<List<BaseApiErrorModel>>(
                    errorBody,
                    object : TypeToken<List<BaseApiErrorModel>>() {}.type
                )
                throw RuntimeException(errorResponse.firstOrNull()?.errorMessage ?: "")
            }
        }
    }

    override suspend fun login(

        email: String,
        password: String
    ): Result<ResLogin> {
        val result = apiRequest {
            api.login(
                ReqLogin(
                    email = email, password = password
                )
            )
        }
        return if (result is Result.Success) {
            if (result.data != null)
                Result.Success(result.data)
            else
                Result.Error((result as Result.Error).exception)
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getUserList(page: Int): Result<ResUserList?> {
        val result = apiRequest {
            api.getUserList(
                page
            )
        }
        return if(result is Result.Success){
            if (result.data != null)
                Result.Success(result.data)
            else
                Result.Error((result as Result.Error).exception)
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    /*override suspend fun getUserOwnerList(
        customerId: String,
        username: String
    ): Result<List<UserOwnerListModel>> {
        val result = apiRequest {
            api.fetchUserOwnerList(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }
        return if (result is Result.Success) {
            Result.Success(result.data?.map {
                UserOwnerListModel(
                    customerName = it?.custName ?: "",
                    customerId = it?.custId ?: "",
                    userId = it?.userId ?: "",
                )
            } ?: emptyList())
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }*/

}