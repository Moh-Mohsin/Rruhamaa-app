package net.ruhamaa.mobile.data.source

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.source.dto.DonationDto
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest

interface DonationDataSource {

    suspend fun donate(donationRequest: DonationRequest): Result<DonationDto>

    suspend fun getDonations(): Result<List<DonationDto>>
}