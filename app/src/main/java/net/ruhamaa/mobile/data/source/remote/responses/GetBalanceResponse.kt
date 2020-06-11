package net.ruhamaa.mobile.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class GetBalanceResponse(
    @SerializedName("responseCode")
    val code: Int,
    @SerializedName("responseMessage")
    val msg: String,
    @SerializedName("dto")
    val data: Int
)

fun GetBalanceResponse.isSuccessful() = code in 200..299