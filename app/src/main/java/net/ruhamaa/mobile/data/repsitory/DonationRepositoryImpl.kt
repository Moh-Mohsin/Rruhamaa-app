package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.map
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Donation
import net.ruhamaa.mobile.data.source.DonationDataSource
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest
import net.ruhamaa.mobile.util.toDonation

class DonationRepositoryImpl(private val donationDataSource: DonationDataSource) : DonationRepository {
    override suspend fun donate(case: Case, amount: Double, notes: String): Result<Donation> {
        val donationRequest = DonationRequest(amount, notes, case)
        return donationDataSource.donate(donationRequest).map { it.toDonation() }
    }

    override suspend fun getDonations(forceUpdate: Boolean): Result<List<Donation>> {
        return donationDataSource.getDonations().map { donationsDto -> donationsDto.map { it.toDonation() } }
    }
}