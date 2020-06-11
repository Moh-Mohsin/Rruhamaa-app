package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.getOrNull
import net.ruhamaa.mobile.data.map
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Donation
import net.ruhamaa.mobile.data.require
import net.ruhamaa.mobile.data.source.DonationDataSource
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest
import net.ruhamaa.mobile.util.toDonation
import java.lang.Exception

class DonationRepositoryImpl(
    private val donationDataSource: DonationDataSource,
    private val ruhamaaRepository: RuhamaaRepository,
    private val userRepository: UserRepository
) : DonationRepository {
    override suspend fun donate(case: Case, amount: Double, notes: String): Result<Donation> {
        val donationRequest = DonationRequest(amount, notes, case)
        return donationDataSource.donate(donationRequest)
            .map { it.toDonation(userRepository.getUser()!!, case) }
    }

    override suspend fun getDonations(forceUpdate: Boolean): Result<List<Donation>> {
        //TODO: what if the case is not in the repo?
        val cases = ruhamaaRepository.getCases().getOrNull()
            ?: return Result.Error(Exception("Network error"))
        return donationDataSource.getDonations()
            .map { donationsDto ->
                donationsDto.map { donationDto ->
                    donationDto.toDonation(
                        userRepository.getUser()!!,
                        cases.firstOrNull { case -> case.id == donationDto.caseId })
                }
            }
    }
}