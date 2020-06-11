package net.ruhamaa.mobile.data.model

import java.time.temporal.TemporalAmount

data class Donation(
    val amount: Double,
    val info: String?,
    val donor: User,
    val case: Case?,
    val date: String
)