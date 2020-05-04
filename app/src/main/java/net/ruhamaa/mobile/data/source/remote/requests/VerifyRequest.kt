package net.ruhamaa.mobile.data.source.remote.requests

import com.google.gson.annotations.SerializedName

data class VerifyRequest(
    @SerializedName("phoneNumber")
    val phoneNum: String,
    val otp: String
)