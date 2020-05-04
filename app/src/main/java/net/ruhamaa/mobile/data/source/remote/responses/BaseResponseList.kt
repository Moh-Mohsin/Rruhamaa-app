package net.ruhamaa.mobile.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class BaseResponseList <T>(
    @SerializedName("responseCode")
    val code: Int,
    @SerializedName("responseMessage")
    val msg: String,
    @SerializedName("dtos")
    val data: List<T>
)
fun <T> BaseResponseList<T>.isSuccessful() = code in 200..299