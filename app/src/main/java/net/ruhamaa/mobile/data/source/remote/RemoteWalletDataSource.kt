package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.data.repsitory.UserRepository
import net.ruhamaa.mobile.data.source.WalletDataSource
import net.ruhamaa.mobile.data.source.dto.CreditDto
import net.ruhamaa.mobile.data.source.dto.TransactionDto
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaApi
import net.ruhamaa.mobile.data.source.remote.responses.isSuccessful

class RemoteWalletDataSource(
    private val ruhamaaApi: RuhamaaApi,
    private val userRepository: UserRepository
) : WalletDataSource {
    override suspend fun getBalance(): Result<Balance> {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()!!
            val response = ruhamaaApi.getBalance(user.walletId).execute()
            if (response.isSuccessful) {
                val result = response.body()!!
                if (result.isSuccessful()) {
                    Result.Success(Balance(result.data.toDouble()))
                } else {
                    Result.Error(Exception(result.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }

    override suspend fun credit(credit: Credit): Result<Balance> {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()!!
            val creditDto = CreditDto(user.walletId, user.id, user.id, credit.amount)
            val response = ruhamaaApi.credit(creditDto).execute()
            if (response.isSuccessful) {
                val result = response.body()!!
                if (result.isSuccessful()) {
                    Result.Success(Balance(result.data.amount))
                } else {
                    Result.Error(Exception(result.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }

    override suspend fun getTransactions(): Result<List<TransactionDto>> {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()!!
            val response = ruhamaaApi.getTransactions(user.walletId).execute()
            if (response.isSuccessful) {
                val result = response.body()!!
                if (result.isSuccessful()) {
                    Result.Success(response.body()!!.data)
                } else {
                    Result.Error(Exception(result.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }
}