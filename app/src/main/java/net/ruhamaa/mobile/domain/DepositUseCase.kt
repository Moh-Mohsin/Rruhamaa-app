package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.repsitory.WalletRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource

class DepositUseCase(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(credit: Credit): Result<Balance> {
        wrapEspressoIdlingResource {
            return walletRepository.credit(credit)
        }
    }

}