package net.ruhamaa.mobile.data.source.fake

import kotlinx.coroutines.delay
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.data.source.WalletDataSource
import net.ruhamaa.mobile.data.source.dto.TransactionDto
import net.ruhamaa.mobile.data.toSuccess

class FakeWalletDataSource : WalletDataSource {

    private val transactions by lazy { getFakeTransactions() }
    private val balance by lazy { getCurrentBalance() }

    override suspend fun getBalance(): Result<Balance> {
        delay(500)
        return getCurrentBalance().toSuccess()
    }

    override suspend fun credit(credit: Credit): Result<Balance> {
        delay(500)
        return Result.Success(balance)
    }

    override suspend fun getTransactions(): Result<List<TransactionDto>> {
        delay(500)
        return transactions.toSuccess()
    }

    private fun getCurrentBalance() = Balance(200.0)

    private fun getFakeTransactions() = listOf(
        TransactionDto(
            1,
            150.0,
            0,
            "150 was spent from your account",
            null,
            null,
            1,
            "11/6/2020"
        )
    )
}