package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.MainTypeModel
import com.google.gson.annotations.SerializedName

data class ApiMainTypeList(

    @SerializedName("TransactionType")
    val transactionType: List<ApiMainTypeListItem?>? = null,

    @SerializedName("InventoryOptions")
    val inventoryOptions: List<ApiMainTypeListItem?>? = null,

    @SerializedName("SerialControlType")
    val serialControlType: List<ApiMainTypeListItem?>? = null,

    @SerializedName("ItemsType")
    val itemsType: List<ApiMainTypeListItem?>? = null,

    @SerializedName("StatusPO")
    val statusPO: List<ApiMainTypeListItem?>? = null,

    @SerializedName("BarcodeFormat")
    val barcodeFormat: List<ApiMainTypeListItem?>? = null,

    @SerializedName("LineState")
    val lineState: List<ApiMainTypeListItem?>? = null,

    @SerializedName("ERPProcessType")
    val eRPProcessType: List<ApiMainTypeListItem?>? = null,

    @SerializedName("InventoryTypes")
    val inventoryTypes: List<ApiMainTypeListItem?>? = null,

    @SerializedName("SourcePO")
    val sourcePO: List<ApiMainTypeListItem?>? = null
) {
    fun toMainTypeList(): MainTypeModel = MainTypeModel(
        transactionType = transactionType?.map {
            it?.toMainTypeListItem()
        },
        inventoryOptions = inventoryOptions?.map {
            it?.toMainTypeListItem()
        },
        serialControlType = serialControlType?.map {
            it?.toMainTypeListItem()
        },
        itemsType = itemsType?.map {
            it?.toMainTypeListItem()
        },
        statusPO = statusPO?.map {
            it?.toMainTypeListItem()
        },
        barcodeFormat = barcodeFormat?.map {
            it?.toMainTypeListItem()
        },
        lineState = lineState?.map {
            it?.toMainTypeListItem()
        },
        eRPProcessType = eRPProcessType?.map {
            it?.toMainTypeListItem()
        },
        inventoryTypes = inventoryTypes?.map {
            it?.toMainTypeListItem()
        },
        sourcePO = sourcePO?.map {
            it?.toMainTypeListItem()
        },
    )
}

