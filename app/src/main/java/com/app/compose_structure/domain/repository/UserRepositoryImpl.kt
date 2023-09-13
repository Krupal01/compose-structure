package com.app.compose_structure.domain.repository

import com.app.compose_structure.common.PreferenceKey
import com.app.compose_structure.common.Result
import com.app.compose_structure.data.local.ISharedPreferences
import com.app.compose_structure.data.remote.IBaseRemoteService
import com.app.compose_structure.di.IoDispatcher
import com.app.compose_structure.model.CountryModel
import com.app.compose_structure.model.FillCompInfoModel
import com.app.compose_structure.model.FillOwnerModel
import com.app.compose_structure.model.FillUserOptionDataModel
import com.app.compose_structure.model.LocationModel
import com.app.compose_structure.model.MainTypeModel
import com.app.compose_structure.model.UserDataModel
import com.app.compose_structure.model.UserOwnerListModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: ISharedPreferences,
    private val remoteDataSource: IBaseRemoteService,
) : IUserRepository {

    private val gson: Gson = Gson()

    override suspend fun storeUserSetting(userModel: UserDataModel) {
        sharedPreferences.storeValue(PreferenceKey.KEY_USER_SETTING, gson.toJson(userModel))
    }

    override suspend fun getUserSetting(): UserDataModel {
        val data =
            sharedPreferences.getValue(PreferenceKey.KEY_USER_SETTING, gson.toJson(UserDataModel()))
        return gson.fromJson(data, UserDataModel::class.java)
    }

    override suspend fun getUserOwnerList(
        customerId: String,
        username: String
    ): Result<List<UserOwnerListModel>> = withContext(dispatcher) {
        remoteDataSource.getUserOwnerList(customerId, username)
    }


    override suspend fun authenticateUser(
        customerId: String,
        username: String,
        password: String
    ): Result<Boolean> = withContext(dispatcher) {
        remoteDataSource.authenticateUser(customerId, username, password).apply {
            if (this is Result.Success && this.data) {
                storeUserSetting(
                    getUserSetting().copy(
                        username = username,
                        owner = customerId
                    )
                )
            }
        }
    }

    override suspend fun getFillUserOptionData(
        customerId: String,
        username: String
    ): Result<FillUserOptionDataModel?> = withContext(dispatcher) {
        remoteDataSource.getFillUserOptionData(customerId, username).apply {
            if (this is Result.Success) {
                storeUserSetting(
                    getUserSetting().copy(
                        owner = customerId
                    )
                )
            }
        }
    }

    override suspend fun logout() {
        sharedPreferences.clear()
    }

    override suspend fun getFillOwner(
        customerId: String,
        username: String
    ): Result<FillOwnerModel?> =
        withContext(dispatcher) {
            remoteDataSource.getFillOwner(customerId, username).apply {
                if (this is Result.Success && this.data != null) {
                    storeOwnerPref(fillOwner = this.data)
                }
            }
        }

    override suspend fun getFillCompInfo(
        customerId: String,
        username: String
    ): Result<FillCompInfoModel?> = withContext(dispatcher) {
        remoteDataSource.getFillCompInfo(customerId, username).apply {
            if (this is Result.Success && this.data != null) {
                storeCompInfoPref(this.data)
            }
        }
    }

    override suspend fun getListLocation(
        customerId: String,
        username: String
    ): Result<List<LocationModel?>?> = withContext(dispatcher) {
        remoteDataSource.getListLocation(customerId, username).apply {
            if (this is Result.Success && this.data != null) {
                storeListLocationPref(this.data.filterNotNull())
            }
        }
    }

    override suspend fun getMainTypesList(
        customerId: String,
        username: String
    ): Result<MainTypeModel?> = withContext(dispatcher) {
        remoteDataSource.getMainTypesList(customerId, username).apply {
            if (this is Result.Success && this.data != null) {
                storeMainTypesListPref(this.data)
            }
        }
    }

    override suspend fun getCountryList(
        lng: Int,
        customerId: String
    ): Result<List<CountryModel?>?> =
        withContext(dispatcher) {
            remoteDataSource.getCountryList(lng, customerId).apply {
                if (this is Result.Success && this.data != null) {
                    storeCountryListPref(this.data.filterNotNull())
                }
            }
        }

    override suspend fun storeOwnerPref(fillOwner: FillOwnerModel) {
        sharedPreferences.storeValue(PreferenceKey.KEY_FILL_OWNER, gson.toJson(fillOwner))
    }

    override suspend fun getOwnerPref(): FillOwnerModel {
        val data =
            sharedPreferences.getValue(PreferenceKey.KEY_FILL_OWNER, gson.toJson(FillOwnerModel()))
        return gson.fromJson(data, FillOwnerModel::class.java)
    }

    override suspend fun storeCompInfoPref(compInfo: FillCompInfoModel) {
        sharedPreferences.storeValue(PreferenceKey.KEY_FILL_COMP_INFO, gson.toJson(compInfo))
    }

    override suspend fun getCompInfoPref(): FillCompInfoModel {
        val data =
            sharedPreferences.getValue(
                PreferenceKey.KEY_FILL_COMP_INFO,
                gson.toJson(FillCompInfoModel())
            )
        return gson.fromJson(data, FillCompInfoModel::class.java)
    }

    override suspend fun storeListLocationPref(listLocation: List<LocationModel>) {
        sharedPreferences.storeValue(PreferenceKey.KEY_LIST_LOCATION, gson.toJson(listLocation))
    }

    override suspend fun getListLocationPref(): List<LocationModel> {
        val data =
            sharedPreferences.getValue(
                PreferenceKey.KEY_LIST_LOCATION,
                gson.toJson(emptyList<LocationModel>())
            )
        return gson.fromJson(data, object : TypeToken<List<LocationModel>>() {}.type)
    }

    override suspend fun storeMainTypesListPref(mainTypeModel: MainTypeModel) {
        sharedPreferences.storeValue(PreferenceKey.KEY_MAIN_TYPE_LIST, gson.toJson(mainTypeModel))
    }

    override suspend fun getMainTypesListPref(): MainTypeModel {
        val data =
            sharedPreferences.getValue(
                PreferenceKey.KEY_MAIN_TYPE_LIST,
                gson.toJson(MainTypeModel())
            )
        return gson.fromJson(data, MainTypeModel::class.java)
    }

    override suspend fun storeCountryListPref(countryModelList: List<CountryModel>) {
        sharedPreferences.storeValue(PreferenceKey.KEY_COUNTRY_LIST, gson.toJson(countryModelList))
    }

    override suspend fun getCountryListPref(): List<CountryModel> {
        val data =
            sharedPreferences.getValue(
                PreferenceKey.KEY_COUNTRY_LIST,
                gson.toJson(emptyList<CountryModel>())
            )
        return gson.fromJson(data, object : TypeToken<List<CountryModel>>() {}.type)
    }


}