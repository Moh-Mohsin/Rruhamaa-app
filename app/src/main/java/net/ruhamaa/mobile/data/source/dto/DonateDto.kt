package net.ruhamaa.mobile.data.source.dto

import com.google.gson.annotations.SerializedName

data class DonateDto(
    val amount: Double,
    @SerializedName("case_id")
    val caseId: Int,
    @SerializedName("created_by_id")
    val createdById: Int,
    @SerializedName("donator_id")
    val donatorId: Int
)