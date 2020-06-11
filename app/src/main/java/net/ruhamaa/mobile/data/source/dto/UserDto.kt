package net.ruhamaa.mobile.data.source.dto

import com.google.gson.annotations.SerializedName

//TODO: map dtos to models instead later
data class UserDto(
        val id: Int,
        val fullName: String,
        @SerializedName("phoneNumber")
        val phoneNum: String,
        val walletId: Int
)
