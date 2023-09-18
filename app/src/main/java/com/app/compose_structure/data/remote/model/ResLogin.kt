package com.app.compose_structure.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResLogin(

    @field:SerializedName("token")
    val token: String? = null
)
