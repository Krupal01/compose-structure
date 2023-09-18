package com.app.compose_structure.data.remote.request

import com.google.gson.annotations.SerializedName

data class ReqLogin(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
