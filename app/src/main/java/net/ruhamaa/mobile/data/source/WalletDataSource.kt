package net.ruhamaa.mobile.data.source

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.source.dto.TransactionDto

interface WalletDataSource {

    suspend fun getBalance(): Result<Balance>

    suspend fun credit(credit: Credit): Result<Balance>

    suspend fun getTransactions(): Result<List<TransactionDto>>
}