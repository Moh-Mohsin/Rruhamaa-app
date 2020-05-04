package net.ruhamaa.mobile.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class BaseResponse <T>(
    @SerializedName("responseCode")
    val code: Int,
    @SerializedName("responseMessage")
    val msg: String,
    @SerializedName("dto")
    val data: T
)
fun <T> BaseResponse<T>.isSuccessful() = code in 200..299