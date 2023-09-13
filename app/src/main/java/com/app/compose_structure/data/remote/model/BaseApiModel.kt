package com.app.compose_structure.data.remote.model

import com.google.gson.annotations.SerializedName

open class BaseApiModel(
    @SerializedName("statusCode")
    val statusCode: String? = null,

    @SerializedName("statusMessage")
    val statusMessage: String? = null,
)