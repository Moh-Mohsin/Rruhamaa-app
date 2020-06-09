package net.ruhamaa.mobile.data.source.fake

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.getOrNull
import net.ruhamaa.mobile.data.require
import net.ruhamaa.mobile.data.source.DonationDataSource
import net.ruhamaa.mobile.data.source.RuhamaDataSource
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.dto.DonationDto
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest
import net.ruhamaa.mobile.data.toSuccess
import net.ruhamaa.mobile.util.format
import net.ruhamaa.mobile.util.getCurrentDateTime
import net.ruhamaa.mobile.util.toCaseDto
import net.ruhamaa.mobile.util.toUserDto

class FakeDonationDataSource(
    private val ruhamaDataSource: RuhamaDataSource,
    private val userDataSource: UserDataSource
) : DonationDataSource {
    var balance = 100
    override suspend fun donate(donationRequest: DonationRequest): Result<DonationDto> {
        return DonationDto(
            donationRequest.amount,
            donationRequest.info,
            userDataSource.getUser().require().toUserDto(),
            donationRequest.case.toCaseDto(),
            getCurrentDateTime().format()
        ).toSuccess()
    }

    override suspend fun getDonations(): Result<List<DonationDto>> {
        return (1..20).map { getFakeDonation() }.toSuccess()
    }

    private suspend fun getFakeDonation() = DonationDto(
        (50..1000).random().toDouble(),
        "",
        userDataSource.getUser().getOrNull()!!.toUserDto(),
        ruhamaDataSource.getCases().getOrNull()!!.random().toCaseDto(),
        getCurrentDateTime().format()
    )
}