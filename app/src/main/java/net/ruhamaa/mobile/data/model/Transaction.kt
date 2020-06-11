package net.ruhamaa.mobile.data.model

data class Transaction(
    val walletId: Int,
    val amount: Double,
    val operation: Int,
    val descrtption: String,
    val fromDate: String?,
    val toDate: String?,
    val createdById: Int,
    val createdAt: String
)