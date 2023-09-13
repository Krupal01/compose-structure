package com.app.compose_structure.model

import java.io.Serializable

data class UsersLocationsListItemModel(
    var app: Any? = null,
    var retMsg: Any? = null,
    var custId: String? = null,
    var userId: String? = null,
    var locId: String? = null,
    var syncLoc: String? = null,
    var connecting: Any? = null
) : Serializable