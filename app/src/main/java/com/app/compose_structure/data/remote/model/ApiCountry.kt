package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.CountryModel
import com.google.gson.annotations.SerializedName

data class ApiCountry(

    @SerializedName("CountryName")
    val countryName: String? = null,

    @SerializedName("CountryCode")
    val countryCode: String? = null,

    @SerializedName("CountryNameFr")
    val countryNameFr: String? = null
) {
    fun toCountry(): CountryModel = CountryModel(
        countryName = countryName,
        countryCode = countryCode,
        countryNameFr = countryNameFr
    )
}
