package net.ruhamaa.mobile.data.model

import com.google.gson.annotations.SerializedName

//TODO: map dtos to models instead later
data class User(
        @SerializedName("phoneNumber")
        val phoneNum: String
)