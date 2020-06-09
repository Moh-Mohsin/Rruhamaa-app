package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.data.Result

interface WalletRepository {

    suspend fun getBalance(): Result<Balance>

    suspend fun credit(credit: Credit): Result<Balance>

    suspend fun getTransactions(forceUpdate: Boolean): Result<List<Transaction>>
}