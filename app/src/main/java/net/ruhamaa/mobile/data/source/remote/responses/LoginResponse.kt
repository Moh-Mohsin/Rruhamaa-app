package net.ruhamaa.mobile.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("phoneNumber")
    val phoneNum: String,
    val otp: String
)