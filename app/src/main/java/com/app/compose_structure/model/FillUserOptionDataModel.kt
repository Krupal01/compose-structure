package com.app.compose_structure.model

import java.io.Serializable

data class FillUserOptionDataModel(
    val generateYN: Boolean? = null,
    val app: Any? = null,
    val pickConfirmYN: Boolean? = null,
    val inventoryAdminYN: Boolean? = null,
    val updGatePass: Boolean? = null,
    val usersLocationsList: List<UsersLocationsListItemModel?>? = null,
    val inventoryYN: Boolean? = null,
    val shipRegTransYN: Boolean? = null,
    val connecting: Any? = null,
    val releaseSerialYN: Boolean? = null,
    val retMsg: Any? = null,
    val modifAllocYN: Boolean? = null,
    val verifyYN: Boolean? = null,
    val custId: String? = null,
    val userId: String? = null,
    val deleteRegTransYN: Boolean? = null,
    val updRegTransYN: Boolean? = null
) : Serializable

