package com.app.compose_structure.data.remote.model

import com.app.compose_structure.model.FillCompInfoModel
import com.google.gson.annotations.SerializedName

data class ApiFillCompInfo(

    @SerializedName("Addr3")
    val addr3: String? = null,

    @SerializedName("App")
    val app: Any? = null,

    @SerializedName("Addr4")
    val addr4: String? = null,

    @SerializedName("Addr1")
    val addr1: String? = null,

    @SerializedName("Addr2")
    val addr2: String? = null,

    @SerializedName("CompName")
    val compName: String? = null,

    @SerializedName("DfltOperTrfOut")
    val dfltOperTrfOut: String? = null,

    @SerializedName("WeightVolume")
    val weightVolume: Any? = null,

    @SerializedName("CompId")
    val compId: String? = null,

    @SerializedName("Connecting")
    val connecting: Any? = null,

    @SerializedName("DfltOperTransitOut")
    val dfltOperTransitOut: String? = null,

    @SerializedName("SMTPPassword")
    val sMTPPassword: Any? = null,

    @SerializedName("SMTPServerPort")
    val sMTPServerPort: Int? = null,

    @SerializedName("WSServerName")
    val wSServerName: Any? = null,

    @SerializedName("CustId")
    val custId: Any? = null,

    @SerializedName("Phone")
    val phone: String? = null,

    @SerializedName("DfltOperTrfIn")
    val dfltOperTrfIn: String? = null,

    @SerializedName("SMTPServer")
    val sMTPServer: String? = null,

    @SerializedName("DfltOperIDIN")
    val dfltOperIDIN: String? = null,

    @SerializedName("DfltOperIDOut")
    val dfltOperIDOut: String? = null,

    @SerializedName("DfltBinCateg")
    val dfltBinCateg: Int? = null,

    @SerializedName("AdvancedServices")
    val advancedServices: Boolean? = null,

    @SerializedName("WSReqMasterPass")
    val wSReqMasterPass: String? = null,

    @SerializedName("SysCurr")
    val sysCurr: String? = null,

    @SerializedName("Mobile")
    val mobile: String? = null,

    @SerializedName("RoundingPrecision")
    val roundingPrecision: Int? = null,

    @SerializedName("Logo")
    val logo: String? = null,

    @SerializedName("WSConnecting")
    val wSConnecting: Any? = null,

    @SerializedName("RetMsg")
    val retMsg: Any? = null,

    @SerializedName("BaseCurr")
    val baseCurr: String? = null,

    @SerializedName("DfltOperTransitIn")
    val dfltOperTransitIn: String? = null,

    @SerializedName("UserId")
    val userId: Any? = null,

    @SerializedName("InterfaceLanguage")
    val interfaceLanguage: Int? = null,

    @SerializedName("ServerName")
    val serverName: Any? = null,

    @SerializedName("VolumePrecision")
    val volumePrecision: Int? = null,

    @SerializedName("MyLocation")
    val myLocation: String? = null,

    @SerializedName("DBName")
    val dBName: Any? = null,

    @SerializedName("DfltOperIDReturn")
    val dfltOperIDReturn: String? = null,

    @SerializedName("Fax")
    val fax: String? = null,

    @SerializedName("WSDBName")
    val wSDBName: Any? = null,

    @SerializedName("SMTPUserName")
    val sMTPUserName: String? = null
) {
    fun toFillCompInfo(): FillCompInfoModel = FillCompInfoModel(
        addr3 = addr3,
        app = app,
        addr4 = addr4,
        addr1 = addr1,
        addr2 = addr2,
        compName = compName,
        dfltOperTrfOut = dfltOperTrfOut,
        weightVolume = weightVolume,
        compId = compId,
        connecting = connecting,
        dfltOperTransitOut = dfltOperTransitOut,
        sMTPPassword = sMTPPassword,
        sMTPServerPort = sMTPServerPort,
        wSServerName = wSServerName,
        custId = custId,
        phone = phone,
        dfltOperTrfIn = dfltOperTrfIn,
        sMTPServer = sMTPServer,
        dfltOperIDIN = dfltOperIDIN,
        dfltOperIDOut = dfltOperIDOut,
        dfltBinCateg = dfltBinCateg,
        advancedServices = advancedServices,
        wSReqMasterPass = wSReqMasterPass,
        sysCurr = sysCurr,
        mobile = mobile,
        roundingPrecision = roundingPrecision,
        logo = logo,
        wSConnecting = wSConnecting,
        retMsg = retMsg,
        baseCurr = baseCurr,
        dfltOperTransitIn = dfltOperTransitIn,
        userId = userId,
        interfaceLanguage = interfaceLanguage,
        serverName = serverName,
        volumePrecision = volumePrecision,
        myLocation = myLocation,
        dBName = dBName,
        dfltOperIDReturn = dfltOperIDReturn,
        fax = fax,
        wSDBName = wSDBName,
        sMTPUserName = sMTPUserName
    )
}
