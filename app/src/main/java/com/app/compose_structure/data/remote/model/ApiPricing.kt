package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.PricingModel
import com.google.gson.annotations.SerializedName

data class ApiPricing(

    @SerializedName("App")
    val app: Any? = null,

    @SerializedName("RetMsg")
    val retMsg: Any? = null,

    @SerializedName("PricingSpecialDetails")
    val pricingSpecialDetails: List<Any?>? = null,

    @SerializedName("UserId")
    val userId: Any? = null,

    @SerializedName("PricingDetails")
    val pricingDetails: List<Any?>? = null,

    @SerializedName("Connecting")
    val connecting: Any? = null
) {
    fun toPricing(): PricingModel = PricingModel(
        app = app,
        retMsg = retMsg,
        pricingSpecialDetails = pricingSpecialDetails,
        userId = userId,
        pricingDetails = pricingDetails,
        connecting = connecting
    )
}