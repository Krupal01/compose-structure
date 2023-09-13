package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.UsersLocationsListItemModel
import com.google.gson.annotations.SerializedName

data class ApiUsersLocationsListItem(

    @SerializedName("App")
    val app: Any? = null,

    @SerializedName("RetMsg")
    val retMsg: Any? = null,

    @SerializedName("CustId")
    val custId: String? = null,

    @SerializedName("UserId")
    val userId: String? = null,

    @SerializedName("LocId")
    val locId: String? = null,

    @SerializedName("SyncLoc")
    val syncLoc: String? = null,

    @SerializedName("Connecting")
    val connecting: Any? = null
) {
    fun toUsersLocationsListItem(): UsersLocationsListItemModel = UsersLocationsListItemModel(

        app = app ?: Any(),

        retMsg = retMsg ?: Any(),

        custId = custId ?: "",

        userId = userId ?: "",

        locId = locId ?: "",

        syncLoc = syncLoc ?: "",

        connecting = connecting ?: Any()
    )
}