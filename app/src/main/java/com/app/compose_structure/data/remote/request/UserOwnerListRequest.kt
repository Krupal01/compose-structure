package com.app.compose_structure.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserOwnerListRequest(
    @SerializedName("CustId")
    val custId: String,
    @SerializedName("UserId")
    val userId: String
) : Serializable