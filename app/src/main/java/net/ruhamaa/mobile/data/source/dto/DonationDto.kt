package net.ruhamaa.mobile.data.source.dto

import net.ruhamaa.mobile.data.model.Case
//
//data class DonationDto(
//    val amount: Double,
//    val info: String,
//    val donorDto: UserDto,
//    val caseDto: CaseDto,
//    val date: String
//)

data class DonationDto(
    val id: Int,
    val donatorId: Int?,
    val caseId: Int?,
    val amount: Double,
    val startDate: String?,
    val endDate: String?,
    val newCaseId: Int?,
    val info: String?,
    val createdById: Int?,
    val donationDate: String?
)