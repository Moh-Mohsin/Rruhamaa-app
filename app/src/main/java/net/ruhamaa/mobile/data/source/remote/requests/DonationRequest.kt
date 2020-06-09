package net.ruhamaa.mobile.data.source.remote.requests

import com.google.gson.annotations.SerializedName
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.source.dto.UserDto

data class DonationRequest(
    val amount: Double,
    val info: String,
    val case: Case
)