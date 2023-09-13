package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.LocationModel
import com.google.gson.annotations.SerializedName

data class ApiLocation(

    @SerializedName("LocDesc")
    val locDesc: String? = null,

    @SerializedName("LocId")
    val locId: String? = null
) {
    fun toLocationModel(): LocationModel = LocationModel(locDesc = locDesc, locId = locId)
}
