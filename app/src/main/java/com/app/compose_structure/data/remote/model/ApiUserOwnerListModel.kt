package com.app.compose_structure.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiUserOwnerListModel(

	@SerializedName("CustName")
	val custName: String? = null,

	@SerializedName("CustId")
	val custId: String? = null,

	@SerializedName("UserId")
	val userId: String? = null
)
