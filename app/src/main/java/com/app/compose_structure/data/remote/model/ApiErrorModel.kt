package com.app.compose_structure.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiErrorModel(

    @field:SerializedName("statusMessage")
    val statusMessage: String? = null,

    @field:SerializedName("statusCode")
    val statusCode: String? = null
)