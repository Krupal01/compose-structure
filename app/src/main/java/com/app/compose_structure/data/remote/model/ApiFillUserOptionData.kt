package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.FillUserOptionDataModel
import com.google.gson.annotations.SerializedName

data class ApiFillUserOptionData(

    @SerializedName("GenerateYN")
    val generateYN: Boolean? = null,

    @SerializedName("App")
    val app: Any? = null,

    @SerializedName("PickConfirmYN")
    val pickConfirmYN: Boolean? = null,

    @SerializedName("InventoryAdminYN")
    val inventoryAdminYN: Boolean? = null,

    @SerializedName("UpdGatePass")
    val updGatePass: Boolean? = null,

    @SerializedName("UsersLocationsList")
    val usersLocationsList: List<ApiUsersLocationsListItem?>? = null,

    @SerializedName("InventoryYN")
    val inventoryYN: Boolean? = null,

    @SerializedName("ShipRegTransYN")
    val shipRegTransYN: Boolean? = null,

    @SerializedName("Connecting")
    val connecting: Any? = null,

    @SerializedName("ReleaseSerialYN")
    val releaseSerialYN: Boolean? = null,

    @SerializedName("RetMsg")
    val retMsg: Any? = null,

    @SerializedName("ModifAllocYN")
    val modifAllocYN: Boolean? = null,

    @SerializedName("VerifyYN")
    val verifyYN: Boolean? = null,

    @SerializedName("CustId")
    val custId: String? = null,

    @SerializedName("UserId")
    val userId: String? = null,

    @SerializedName("DeleteRegTransYN")
    val deleteRegTransYN: Boolean? = null,

    @SerializedName("UpdRegTransYN")
    val updRegTransYN: Boolean? = null
) {
    fun toFillUserOptionData(): FillUserOptionDataModel = FillUserOptionDataModel(
        generateYN = this.generateYN ?: false,
        app = this.app ?: false,
        pickConfirmYN = this.pickConfirmYN ?: false,
        inventoryAdminYN = this.inventoryAdminYN ?: false,
        updGatePass = this.updGatePass ?: false,
        usersLocationsList = this.usersLocationsList?.map {it?.toUsersLocationsListItem()} ?: emptyList(),
        inventoryYN = this.inventoryYN ?: false,
        shipRegTransYN = this.shipRegTransYN ?: false,
        connecting = this.connecting ?: false,
        releaseSerialYN = this.releaseSerialYN ?: false,
        retMsg = this.retMsg ?: false,
        modifAllocYN = this.modifAllocYN ?: false,
        verifyYN = this.verifyYN ?: false,
        custId = this.custId ?: "",
        userId = this.userId ?: "",
        deleteRegTransYN = this.deleteRegTransYN ?: false,
        updRegTransYN = this.updRegTransYN ?: false,
    )
}

