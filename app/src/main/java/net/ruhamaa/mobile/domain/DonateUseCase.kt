package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Donation
import net.ruhamaa.mobile.data.repsitory.DonationRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource

class DonateUseCase(
    private val donationRepository: DonationRepository
) {
    suspend operator fun invoke(case: Case, amount: Double, notes: String): Result<Donation> {
        wrapEspressoIdlingResource {
            return donationRepository.donate(case, amount, notes)
        }
    }

}