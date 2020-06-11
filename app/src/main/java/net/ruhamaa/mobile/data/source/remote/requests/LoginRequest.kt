package net.ruhamaa.mobile.data.source.remote.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phoneNumber")
    val phoneNum: String,
    val agent: Boolean
)