package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.FillOwnerModel
import com.google.gson.annotations.SerializedName

data class ApiFillOwner(

    @SerializedName("App")
    val app: Any? = null,

    @SerializedName("RelabelingPerSKUPrice")
    val relabelingPerSKUPrice: Any? = null,

    @SerializedName("TransUsrFld1")
    val transUsrFld1: String? = null,

    @SerializedName("ReturnDestReexpPrice")
    val returnDestReexpPrice: Any? = null,

    @SerializedName("DfltShipMethod")
    val dfltShipMethod: String? = null,

    @SerializedName("LabelSpecialQty")
    val labelSpecialQty: Any? = null,

    @SerializedName("ArchXMLPath")
    val archXMLPath: String? = null,

    @SerializedName("LabelSpecialPrice")
    val labelSpecialPrice: Any? = null,

    @SerializedName("FilterPhysLoc")
    val filterPhysLoc: Boolean? = null,

    @SerializedName("CustId")
    val custId: String? = null,

    @SerializedName("ForceDfltLoc")
    val forceDfltLoc: Boolean? = null,

    @SerializedName("ForceCountryOfOrigin")
    val forceCountryOfOrigin: Boolean? = null,

    @SerializedName("PickListDispatchYN")
    val pickListDispatchYN: Boolean? = null,

    @SerializedName("PickListFormat")
    val pickListFormat: Int? = null,

    @SerializedName("BCC")
    val bCC: String? = null,

    @SerializedName("ItemUdf2")
    val itemUdf2: Boolean? = null,

    @SerializedName("ItemUdf3")
    val itemUdf3: Boolean? = null,

    @SerializedName("ItemUdf1")
    val itemUdf1: Boolean? = null,

    @SerializedName("ForceStCond")
    val forceStCond: Boolean? = null,

    @SerializedName("SearchBySerial")
    val searchBySerial: Boolean? = null,

    @SerializedName("TransUdf1")
    val transUdf1: Boolean? = null,

    @SerializedName("DfltLocOut")
    val dfltLocOut: String? = null,

    @SerializedName("TransUdf2")
    val transUdf2: Boolean? = null,

    @SerializedName("TransUdf3")
    val transUdf3: Boolean? = null,

    @SerializedName("TransUdf4")
    val transUdf4: Boolean? = null,

    @SerializedName("ImportFormat")
    val importFormat: String? = null,

    @SerializedName("Sender")
    val sender: String? = null,

    @SerializedName("DfltBinId")
    val dfltBinId: String? = null,

    @SerializedName("WebLinkToTrv")
    val webLinkToTrv: Boolean? = null,

    @SerializedName("QCInspPrice")
    val qCInspPrice: Any? = null,

    @SerializedName("TransUsrFld4")
    val transUsrFld4: String? = null,

    @SerializedName("TransUsrFld3")
    val transUsrFld3: String? = null,

    @SerializedName("ItemUdf4")
    val itemUdf4: Boolean? = null,

    @SerializedName("TransUsrFld2")
    val transUsrFld2: String? = null,

    @SerializedName("TransactionFormat")
    val transactionFormat: Int? = null,

    @SerializedName("ValidCountTrvYN")
    val validCountTrvYN: Boolean? = null,

    @SerializedName("CompSynch")
    val compSynch: String? = null,

    @SerializedName("FileName")
    val fileName: String? = null,

    @SerializedName("RelabelingRequestPrice")
    val relabelingRequestPrice: Any? = null,

    @SerializedName("PLBaseOn")
    val pLBaseOn: Int? = null,

    @SerializedName("ForceBOExit")
    val forceBOExit: Boolean? = null,

    @SerializedName("Connecting")
    val connecting: Any? = null,

    @SerializedName("UseLotforBOE")
    val useLotforBOE: Boolean? = null,

    @SerializedName("ReturnProcQty")
    val returnProcQty: Any? = null,

    @SerializedName("PickListPaperOrientation")
    val pickListPaperOrientation: Int? = null,

    @SerializedName("PickListControlYN")
    val pickListControlYN: Boolean? = null,

    @SerializedName("LockReception")
    val lockReception: Boolean? = null,

    @SerializedName("DfltUom")
    val dfltUom: String? = null,

    @SerializedName("RcpMedPrice")
    val rcpMedPrice: Any? = null,

    @SerializedName("SendPurchByMail")
    val sendPurchByMail: Boolean? = null,

    @SerializedName("ExportPath")
    val exportPath: String? = null,

    @SerializedName("ReturnReconcPrice")
    val returnReconcPrice: Any? = null,

    @SerializedName("CC")
    val cC: String? = null,

    @SerializedName("DfltLengthUnit")
    val dfltLengthUnit: String? = null,

    @SerializedName("XMLPath")
    val xMLPath: String? = null,

    @SerializedName("AllowAppTrans")
    val allowAppTrans: Boolean? = null,

    @SerializedName("ValidateByItemSerial")
    val validateByItemSerial: Boolean? = null,

    @SerializedName("UseLogger")
    val useLogger: Boolean? = null,

    @SerializedName("LockPickingListYN")
    val lockPickingListYN: Boolean? = null,

    @SerializedName("RcpAncCTPrice")
    val rcpAncCTPrice: Any? = null,

    @SerializedName("CustName")
    val custName: String? = null,

    @SerializedName("CTOPrinterPath")
    val cTOPrinterPath: String? = null,

    @SerializedName("LabelAssemblyPrice")
    val labelAssemblyPrice: Any? = null,

    @SerializedName("ERPProcess")
    val eRPProcess: Int? = null,

    @SerializedName("DispReference")
    val dispReference: Boolean? = null,

    @SerializedName("UseLotByCompanyYN")
    val useLotByCompanyYN: Boolean? = null,

    @SerializedName("Addr3")
    val addr3: String? = null,

    @SerializedName("Addr4")
    val addr4: String? = null,

    @SerializedName("Addr1")
    val addr1: String? = null,

    @SerializedName("Addr2")
    val addr2: String? = null,

    @SerializedName("AllowDelivNoteEntry")
    val allowDelivNoteEntry: Boolean? = null,

    @SerializedName("ReceiveByDN")
    val receiveByDN: Boolean? = null,

    @SerializedName("PickingBySO")
    val pickingBySO: Boolean? = null,

    @SerializedName("ProjectByBinYN")
    val projectByBinYN: Boolean? = null,

    @SerializedName("ArchPickPath")
    val archPickPath: String? = null,

    @SerializedName("DfltCargoDtl")
    val dfltCargoDtl: Int? = null,

    @SerializedName("FIFOYn")
    val fIFOYn: Boolean? = null,

    @SerializedName("PickPackDispatchPrice")
    val pickPackDispatchPrice: Any? = null,

    @SerializedName("Interf8SensYN")
    val interf8SensYN: Boolean? = null,

    @SerializedName("PickRecipient")
    val pickRecipient: String? = null,

    @SerializedName("DfltLocIn")
    val dfltLocIn: String? = null,

    @SerializedName("DfltBinOut")
    val dfltBinOut: String? = null,

    @SerializedName("MixBatches")
    val mixBatches: Boolean? = null,

    @SerializedName("PreAlertYN")
    val preAlertYN: Boolean? = null,

    @SerializedName("AncillariesPickPackDispatchPrice")
    val ancillariesPickPackDispatchPrice: Any? = null,

    @SerializedName("DfltLocId")
    val dfltLocId: String? = null,

    @SerializedName("Recipient")
    val recipient: String? = null,

    @SerializedName("TrfToTRVClosingYN")
    val trfToTRVClosingYN: Boolean? = null,

    @SerializedName("ExportFormat")
    val exportFormat: String? = null,

    @SerializedName("DispDelivType")
    val dispDelivType: Boolean? = null,

    @SerializedName("RcpMedQty")
    val rcpMedQty: Any? = null,

    @SerializedName("PickPackDispatchQty")
    val pickPackDispatchQty: Any? = null,

    @SerializedName("RcpAncCTQty")
    val rcpAncCTQty: Any? = null,

    @SerializedName("UseSupplierBOE")
    val useSupplierBOE: Boolean? = null,

    @SerializedName("DfltWeightUnit")
    val dfltWeightUnit: String? = null,

    @SerializedName("BarcodeYN")
    val barcodeYN: Boolean? = null,

    @SerializedName("PickPath")
    val pickPath: String? = null,

    @SerializedName("ActivateSpaceMgmt")
    val activateSpaceMgmt: Boolean? = null,

    @SerializedName("AllowCompleteTrans")
    val allowCompleteTrans: Boolean? = null,

    @SerializedName("UseEAN128")
    val useEAN128: Boolean? = null,

    @SerializedName("UseBinLocation")
    val useBinLocation: Boolean? = null,

    @SerializedName("Interf8SensDirectYN")
    val interf8SensDirectYN: Boolean? = null,

    @SerializedName("DfltBinIn")
    val dfltBinIn: String? = null,

    @SerializedName("GlbInventoryByBin")
    val glbInventoryByBin: Boolean? = null,

    @SerializedName("SerialControl")
    val serialControl: Int? = null,

    @SerializedName("CustomDocsFormat")
    val customDocsFormat: Int? = null,

    @SerializedName("UsePalLPN")
    val usePalLPN: Boolean? = null,

    @SerializedName("ItemLabelFormat")
    val itemLabelFormat: Int? = null,

    @SerializedName("Pricing")
    val pricing: ApiPricing? = null,

    @SerializedName("RecipientName")
    val recipientName: String? = null,

    @SerializedName("WSHttpAddress")
    val wSHttpAddress: String? = null,

    @SerializedName("ItemUsrFld2")
    val itemUsrFld2: String? = null,

    @SerializedName("FilePath")
    val filePath: String? = null,

    @SerializedName("ItemUsrFld1")
    val itemUsrFld1: String? = null,

    @SerializedName("ItemUsrFld4")
    val itemUsrFld4: String? = null,

    @SerializedName("ShowExpiryDate")
    val showExpiryDate: Boolean? = null,

    @SerializedName("ItemUsrFld3")
    val itemUsrFld3: String? = null,

    @SerializedName("ReturnProcPrice")
    val returnProcPrice: Any? = null,

    @SerializedName("ArchPurchPath")
    val archPurchPath: String? = null,

    @SerializedName("WSUser")
    val wSUser: String? = null,

    @SerializedName("Logo")
    val logo: String? = null,

    @SerializedName("DfltVolumeUnit")
    val dfltVolumeUnit: String? = null,

    @SerializedName("LabelRequestPrice")
    val labelRequestPrice: Any? = null,

    @SerializedName("PickRecipientName")
    val pickRecipientName: String? = null,

    @SerializedName("SendPickByMail")
    val sendPickByMail: Boolean? = null,

    @SerializedName("RetMsg")
    val retMsg: Any? = null,

    @SerializedName("AncillariesPickPackDispatchQty")
    val ancillariesPickPackDispatchQty: Any? = null,

    @SerializedName("PurchPath")
    val purchPath: String? = null,

    @SerializedName("UserId")
    val userId: Any? = null,

    @SerializedName("LabelPrinterPath")
    val labelPrinterPath: String? = null,

    @SerializedName("RegionId")
    val regionId: String? = null
) {
    fun toFillOwner(): FillOwnerModel = FillOwnerModel(
        app = app,
        relabelingPerSKUPrice = relabelingPerSKUPrice,
        transUsrFld1 = transUsrFld1,
        returnDestReexpPrice = returnDestReexpPrice,
        dfltShipMethod = dfltShipMethod,
        labelSpecialQty = labelSpecialQty,
        archXMLPath = archXMLPath,
        labelSpecialPrice = labelSpecialPrice,
        filterPhysLoc = filterPhysLoc,
        custId = custId,
        forceDfltLoc = forceDfltLoc,
        forceCountryOfOrigin = forceCountryOfOrigin,
        pickListDispatchYN = pickListDispatchYN,
        pickListFormat = pickListFormat,
        bCC = bCC,
        itemUdf2 = itemUdf2,
        itemUdf3 = itemUdf3,
        itemUdf1 = itemUdf1,
        forceStCond = forceStCond,
        searchBySerial = searchBySerial,
        transUdf1 = transUdf1,
        dfltLocOut = dfltLocOut,
        transUdf2 = transUdf2,
        transUdf3 = transUdf3,
        transUdf4 = transUdf4,
        importFormat = importFormat,
        sender = sender,
        dfltBinId = dfltBinId,
        webLinkToTrv = webLinkToTrv,
        qCInspPrice = qCInspPrice,
        transUsrFld4 = transUsrFld4,
        transUsrFld3 = transUsrFld3,
        itemUdf4 = itemUdf4,
        transUsrFld2 = transUsrFld2,
        transactionFormat = transactionFormat,
        validCountTrvYN = validCountTrvYN,
        compSynch = compSynch,
        fileName = fileName,
        relabelingRequestPrice = relabelingRequestPrice,
        pLBaseOn = pLBaseOn,
        forceBOExit = forceBOExit,
        connecting = connecting,
        useLotforBOE = useLotforBOE,
        returnProcQty = returnProcQty,
        pickListPaperOrientation = pickListPaperOrientation,
        pickListControlYN = pickListControlYN,
        lockReception = lockReception,
        dfltUom = dfltUom,
        rcpMedPrice = rcpMedPrice,
        sendPurchByMail = sendPurchByMail,
        exportPath = exportPath,
        returnReconcPrice = returnReconcPrice,
        cC = cC,
        dfltLengthUnit = dfltLengthUnit,
        xMLPath = xMLPath,
        allowAppTrans = allowAppTrans,
        validateByItemSerial = validateByItemSerial,
        useLogger = useLogger,
        lockPickingListYN = lockPickingListYN,
        rcpAncCTPrice = rcpAncCTPrice,
        custName = custName,
        cTOPrinterPath = cTOPrinterPath,
        labelAssemblyPrice = labelAssemblyPrice,
        eRPProcess = eRPProcess,
        dispReference = dispReference,
        useLotByCompanyYN = useLotByCompanyYN,
        addr3 = addr3,
        addr4 = addr4,
        addr1 = addr1,
        addr2 = addr2,
        allowDelivNoteEntry = allowDelivNoteEntry,
        receiveByDN = receiveByDN,
        pickingBySO = pickingBySO,
        projectByBinYN = projectByBinYN,
        archPickPath = archPickPath,
        dfltCargoDtl = dfltCargoDtl,
        fIFOYn = fIFOYn,
        pickPackDispatchPrice = pickPackDispatchPrice,
        interf8SensYN = interf8SensYN,
        pickRecipient = pickRecipient,
        dfltLocIn = dfltLocIn,
        dfltBinOut = dfltBinOut,
        mixBatches = mixBatches,
        preAlertYN = preAlertYN,
        ancillariesPickPackDispatchPrice = ancillariesPickPackDispatchPrice,
        dfltLocId = dfltLocId,
        recipient = recipient,
        trfToTRVClosingYN = trfToTRVClosingYN,
        exportFormat = exportFormat,
        dispDelivType = dispDelivType,
        rcpMedQty = rcpMedQty,
        pickPackDispatchQty = pickPackDispatchQty,
        rcpAncCTQty = rcpAncCTQty,
        useSupplierBOE = useSupplierBOE,
        dfltWeightUnit = dfltWeightUnit,
        barcodeYN = barcodeYN,
        pickPath = pickPath,
        activateSpaceMgmt = activateSpaceMgmt,
        allowCompleteTrans = allowCompleteTrans,
        useEAN128 = useEAN128,
        useBinLocation = useBinLocation,
        interf8SensDirectYN = interf8SensDirectYN,
        dfltBinIn = dfltBinIn,
        glbInventoryByBin = glbInventoryByBin,
        serialControl = serialControl,
        customDocsFormat = customDocsFormat,
        usePalLPN = usePalLPN,
        itemLabelFormat = itemLabelFormat,
        pricing = pricing?.toPricing(),
        recipientName = recipientName,
        wSHttpAddress = wSHttpAddress,
        itemUsrFld2 = itemUsrFld2,
        filePath = filePath,
        itemUsrFld1 = itemUsrFld1,
        itemUsrFld4 = itemUsrFld4,
        showExpiryDate = showExpiryDate,
        itemUsrFld3 = itemUsrFld3,
        returnProcPrice = returnProcPrice,
        archPurchPath = archPurchPath,
        wSUser = wSUser,
        logo = logo,
        dfltVolumeUnit = dfltVolumeUnit,
        labelRequestPrice = labelRequestPrice,
        pickRecipientName = pickRecipientName,
        sendPickByMail = sendPickByMail,
        retMsg = retMsg,
        ancillariesPickPackDispatchQty = ancillariesPickPackDispatchQty,
        purchPath = purchPath,
        userId = userId,
        labelPrinterPath = labelPrinterPath,
        regionId = regionId
    )
}

