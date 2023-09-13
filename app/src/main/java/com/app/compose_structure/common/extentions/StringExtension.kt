package com.app.compose_structure.common.extentions

import java.util.Base64

fun String.toBase64Decode(): String {
    return Base64.getDecoder().decode(this).toString()
}

fun String.toBase64Encode(): String {
    return Base64.getEncoder().encodeToString(this.toByteArray())
}