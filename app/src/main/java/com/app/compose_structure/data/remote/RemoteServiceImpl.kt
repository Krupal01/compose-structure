package com.app.compose_structure.data.remote

import com.app.compose_structure.R
import com.app.compose_structure.common.CustomException
import com.app.compose_structure.common.Result
import com.app.compose_structure.common.SUCCESS
import com.app.compose_structure.data.remote.model.ApiErrorModel
import com.app.compose_structure.data.remote.model.BaseApiErrorModel
import com.app.compose_structure.data.remote.request.AuthenticateUserRequest
import com.app.compose_structure.data.remote.request.CountryListRequest
import com.app.compose_structure.data.remote.request.UserOwnerListRequest
import com.app.compose_structure.model.CountryModel
import com.app.compose_structure.model.FillCompInfoModel
import com.app.compose_structure.model.FillOwnerModel
import com.app.compose_structure.model.FillUserOptionDataModel
import com.app.compose_structure.model.LocationModel
import com.app.compose_structure.model.MainTypeModel
import com.app.compose_structure.model.UserOwnerListModel
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
            if (networkResponse.code() == 401){
                val errorBody = networkResponse.errorBody()?.string()
                val errorResponse = Gson().fromJson<ApiErrorModel>(
                    errorBody,
                    ApiErrorModel::class.java
                )
                throw CustomException(message = errorResponse.statusMessage ?: "")
            }
            else {
                val errorBody = networkResponse.errorBody()?.string()
                val errorResponse = Gson().fromJson<List<BaseApiErrorModel>>(
                    errorBody,
                    object : TypeToken<List<BaseApiErrorModel>>() {}.type
                )
                throw RuntimeException(errorResponse.firstOrNull()?.errorMessage ?: "")
            }
        }
    }

    override suspend fun getUserOwnerList(
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
    }

    override suspend fun authenticateUser(
        customerId: String,
        username: String,
        password: String
    ): Result<Boolean> {
        val result = apiRequest {
            api.authenticateUser(
                AuthenticateUserRequest(
                    customerId, username, password
                )
            )
        }
        return if (result is Result.Success) {
            if (result.data?.statusCode == SUCCESS)
                Result.Success(true)
            else
                Result.Error(Exception(result.data?.statusMessage ?: ""))
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getFillUserOptionData(
        customerId: String,
        username: String
    ): Result<FillUserOptionDataModel?> {
        val result = apiRequest {
            api.fetchFillUserOptionData(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.toFillUserOptionData())
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getFillOwner(
        customerId: String,
        username: String
    ): Result<FillOwnerModel?> {
        val result = apiRequest {
            api.fetchFillOwner(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.toFillOwner())
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getFillCompInfo(
        customerId: String,
        username: String
    ): Result<FillCompInfoModel?> {
        val result = apiRequest {
            api.fetchFillCompInfo(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.toFillCompInfo())
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getListLocation(
        customerId: String,
        username: String
    ): Result<List<LocationModel?>?> {
        val result = apiRequest {
            api.fetchListLocation(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.map {
                it?.toLocationModel()
            })
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getMainTypesList(
        customerId: String,
        username: String
    ): Result<MainTypeModel?> {
        val result = apiRequest {
            api.fetchMainTypesList(
                UserOwnerListRequest(
                    customerId, username
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.toMainTypeList())
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }

    override suspend fun getCountryList(
        lng: Int,
        customerId: String
    ): Result<List<CountryModel?>?> {
        val result = apiRequest {
            api.fetchCountryList(
                CountryListRequest(
                    customerId, lng
                )
            )
        }

        return if (result is Result.Success) {
            Result.Success(result.data?.map { it?.toCountry() })
        } else {
            Result.Error((result as Result.Error).exception)
        }
    }


}