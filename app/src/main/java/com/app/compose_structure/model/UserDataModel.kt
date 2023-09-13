package com.app.compose_structure.model

import com.app.compose_structure.common.HOST_URL
import com.app.compose_structure.common.OWNER
import com.app.compose_structure.common.USERNAME
import java.io.Serializable

data class UserDataModel(
    val username: String = "",
    val owner: String= "",

    val settingUsername: String = USERNAME,
    val settingOwner: String = OWNER,
    val settingWebUrl: String = HOST_URL,

    val isUseScandiTestingKey: Boolean = false,
    val isMultiScanEnable: Boolean = false
): Serializable