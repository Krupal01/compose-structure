package com.app.compose_structure.model

import java.io.Serializable

data class MainTypeModel(
    val transactionType: List<MainTypeListItem?>? = null,
    val inventoryOptions: List<MainTypeListItem?>? = null,
    val serialControlType: List<MainTypeListItem?>? = null,
    val itemsType: List<MainTypeListItem?>? = null,
    val statusPO: List<MainTypeListItem?>? = null,
    val barcodeFormat: List<MainTypeListItem?>? = null,
    val lineState: List<MainTypeListItem?>? = null,
    val eRPProcessType: List<MainTypeListItem?>? = null,
    val inventoryTypes: List<MainTypeListItem?>? = null,
    val sourcePO: List<MainTypeListItem?>? = null
) : Serializable

