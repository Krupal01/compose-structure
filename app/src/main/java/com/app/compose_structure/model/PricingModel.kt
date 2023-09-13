package com.app.compose_structure.model

import java.io.Serializable

data class PricingModel(
    val app: Any? = null,
    val retMsg: Any? = null,
    val pricingSpecialDetails: List<Any?>? = null,
    val userId: Any? = null,
    val pricingDetails: List<Any?>? = null,
    val connecting: Any? = null
) : Serializable