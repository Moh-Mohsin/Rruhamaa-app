package net.ruhamaa.mobile.data.source.dto

import net.ruhamaa.mobile.data.model.Case

data class DonationDto(
    val amount: Double,
    val info: String,
    val donorDto: UserDto,
    val caseDto: CaseDto,
    val date: String
)