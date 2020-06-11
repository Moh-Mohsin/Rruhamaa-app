package net.ruhamaa.mobile.data.source.dto

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("wallet_id")
    val walletId: Int,
    val amount: Double,
    val operation: Int,
    val descrtption: String,
    @SerializedName("from_date")
    val fromDate: String?,
    @SerializedName("to_date")
    val toDate: String?,
    @SerializedName("created_by_id")
    val createdById: Int
)