package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.MainTypeListItem
import com.google.gson.annotations.SerializedName

data class ApiMainTypeListItem(

    @SerializedName("Description")
    val description: String? = null,

    @SerializedName("Id")
    val id: Int? = null
) {
    fun toMainTypeListItem(): MainTypeListItem = MainTypeListItem(
        description = description,
        id = id
    )
}