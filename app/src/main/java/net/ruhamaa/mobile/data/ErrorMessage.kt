package net.ruhamaa.mobile.data

sealed class ErrorMessage {
    data class ResMessage(val res: Int): ErrorMessage()
    data class StringMessage(val msg: String): ErrorMessage()
}