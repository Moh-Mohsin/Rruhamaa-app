package net.ruhamaa.mobile.data.model

data class Credit(
    val amount: Double,
    val bankAccountNumber: String,
    val bankBranch: String,
    val transNo: String,
    val imgUrl: String?,
    val notes: String?
)