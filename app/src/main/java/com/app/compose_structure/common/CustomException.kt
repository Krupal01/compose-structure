package com.app.compose_structure.common

data class CustomException(
    override val message: String = "Error",
    val messageResId: Int = 0
) : Exception(message)

data class ConnectionException(
    override val message: String = "Connection Error",
    val messageResId: Int = 0
) : Exception(message)