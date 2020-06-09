package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.data.repsitory.WalletRepository

class GetTransactionsUseCase(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<List<Transaction>> {
        wrapEspressoIdlingResource {
            return walletRepository.getTransactions(forceUpdate)
        }
    }

}