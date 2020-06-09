package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.repsitory.WalletRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource

class GetBalanceUseCase(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(): Result<Balance> {
        wrapEspressoIdlingResource {
            return walletRepository.getBalance()
        }
    }

}