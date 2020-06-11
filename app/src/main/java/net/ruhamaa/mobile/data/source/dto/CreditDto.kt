package net.ruhamaa.mobile.data.source.dto

import com.google.gson.annotations.SerializedName

data class CreditDto(
    val id: Int,
    @SerializedName("owner_id")
    val ownerId: Int,
    @SerializedName("created_by_id")
    val createdById: Int,
    val amount: Double
)