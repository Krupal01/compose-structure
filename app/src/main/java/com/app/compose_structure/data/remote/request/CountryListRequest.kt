package com.app.compose_structure.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryListRequest(
    @SerializedName("CustId")
    val custId: String,
    @SerializedName("IntLng")
    val IntLng: Int = 1
) : Serializable