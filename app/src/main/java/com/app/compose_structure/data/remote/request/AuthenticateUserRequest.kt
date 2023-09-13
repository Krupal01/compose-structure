package com.app.compose_structure.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AuthenticateUserRequest(
    @SerializedName("CustId")
    val custId: String,
    @SerializedName("UserId")
    val userId: String,
    @SerializedName("Password")
    val password: String
) : Serializable