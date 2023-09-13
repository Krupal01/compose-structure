package com.app.compose_structure.data.remote

import com.app.compose_structure.data.remote.model.ApiCountry
import com.app.compose_structure.data.remote.model.ApiFillCompInfo
import com.app.compose_structure.data.remote.model.ApiFillOwner
import com.app.compose_structure.data.remote.model.ApiFillUserOptionData
import com.app.compose_structure.data.remote.model.ApiLocation
import com.app.compose_structure.data.remote.model.ApiMainTypeList
import com.app.compose_structure.data.remote.model.ApiUserOwnerListModel
import com.app.compose_structure.data.remote.model.BaseApiModel
import com.app.compose_structure.data.remote.request.AuthenticateUserRequest
import com.app.compose_structure.data.remote.request.CountryListRequest
import com.app.compose_structure.data.remote.request.UserOwnerListRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("UserOwnerList")
    suspend fun fetchUserOwnerList(
        @Body body: UserOwnerListRequest
    ): Response<List<ApiUserOwnerListModel?>?>

    @POST("AuthenticateUser")
    suspend fun authenticateUser(
        @Body body: AuthenticateUserRequest
    ): Response<BaseApiModel?>

    @POST("FillUserOptions")
    suspend fun fetchFillUserOptionData(
        @Body body: UserOwnerListRequest
    ): Response<ApiFillUserOptionData?>

    @POST("FillOwner")
    suspend fun fetchFillOwner(@Body body: UserOwnerListRequest): Response<ApiFillOwner?>

    @POST("FillCompInfo")
    suspend fun fetchFillCompInfo(@Body body: UserOwnerListRequest): Response<ApiFillCompInfo?>

    @POST("ListLocation")
    suspend fun fetchListLocation(@Body body: UserOwnerListRequest): Response<List<ApiLocation?>?>

    @POST("MainTypesList")
    suspend fun fetchMainTypesList(@Body body: UserOwnerListRequest): Response<ApiMainTypeList?>

    @POST("ListCountry")
    suspend fun fetchCountryList(@Body body: CountryListRequest): Response<List<ApiCountry?>?>
}