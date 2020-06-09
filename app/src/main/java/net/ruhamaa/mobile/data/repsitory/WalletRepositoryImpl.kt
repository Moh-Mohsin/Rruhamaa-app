package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.data.source.WalletDataSource

class WalletRepositoryImpl(private val walletDataSource: WalletDataSource) : WalletRepository {
    override suspend fun getBalance(): Result<Balance> {
        return walletDataSource.getBalance()
    }

    override suspend fun credit(credit: Credit): Result<Balance> {
        return walletDataSource.credit(credit)
    }

    override suspend fun getTransactions(forceUpdate: Boolean): Result<List<Transaction>> {
        // TODO: implement caching of transactions
        return walletDataSource.getTransactions()
     }
}