package com.app.compose_structure.domain.repository

import com.app.compose_structure.common.Result
import com.app.compose_structure.model.CountryModel
import com.app.compose_structure.model.FillCompInfoModel
import com.app.compose_structure.model.FillOwnerModel
import com.app.compose_structure.model.FillUserOptionDataModel
import com.app.compose_structure.model.LocationModel
import com.app.compose_structure.model.MainTypeModel
import com.app.compose_structure.model.UserDataModel
import com.app.compose_structure.model.UserOwnerListModel

interface IUserRepository {

    suspend fun storeUserSetting(userModel: UserDataModel)

    suspend fun getUserSetting(): UserDataModel

    suspend fun getUserOwnerList(
        customerId: String,
        username: String
    ): Result<List<UserOwnerListModel>>

    suspend fun authenticateUser(
        customerId: String,
        username: String,
        password: String
    ): Result<Boolean>

    suspend fun getFillUserOptionData(
        customerId: String,
        username: String
    ): Result<FillUserOptionDataModel?>

    suspend fun logout()

    suspend fun getFillOwner(customerId: String, username: String): Result<FillOwnerModel?>

    suspend fun getFillCompInfo(customerId: String, username: String): Result<FillCompInfoModel?>

    suspend fun getListLocation(customerId: String, username: String): Result<List<LocationModel?>?>

    suspend fun getMainTypesList(customerId: String, username: String): Result<MainTypeModel?>

    suspend fun getCountryList(lng: Int, customerId: String): Result<List<CountryModel?>?>

    suspend fun storeOwnerPref(fillOwner: FillOwnerModel)
    suspend fun getOwnerPref(): FillOwnerModel
    suspend fun storeCompInfoPref(compInfo: FillCompInfoModel)
    suspend fun getCompInfoPref(): FillCompInfoModel
    suspend fun storeListLocationPref(listLocation: List<LocationModel>)
    suspend fun getListLocationPref(): List<LocationModel>
    suspend fun storeMainTypesListPref(mainTypeModel: MainTypeModel)
    suspend fun getMainTypesListPref(): MainTypeModel
    suspend fun storeCountryListPref(countryModelList: List<CountryModel>)
    suspend fun getCountryListPref(): List<CountryModel>
}