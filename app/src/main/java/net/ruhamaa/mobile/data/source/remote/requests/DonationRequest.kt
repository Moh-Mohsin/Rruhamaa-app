package net.ruhamaa.mobile.data.source.remote.requests

import net.ruhamaa.mobile.data.model.Case

data class DonationRequest(
    val amount: Double,
    val info: String,
    val case: Case
)