package com.app.compose_structure.model

import java.io.Serializable


data class CountryModel(
    val countryName: String? = null,
    val countryCode: String? = null,
    val countryNameFr: String? = null
) : Serializable
