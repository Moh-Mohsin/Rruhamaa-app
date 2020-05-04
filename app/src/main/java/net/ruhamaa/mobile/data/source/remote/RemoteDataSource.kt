package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.RuhamaDataSource
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaApi
import net.ruhamaa.mobile.data.source.remote.requests.LoginRequest
import net.ruhamaa.mobile.data.source.remote.requests.VerifyRequest
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse

class RemoteDataSource(private val ruhamaaApi: RuhamaaApi) : RuhamaDataSource {

    override suspend fun forgotPassword(phoneNum: String): Result<Empty> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(user: User): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getCases(): Result<List<Case>> {
        return withContext(Dispatchers.IO) {
            val response = ruhamaaApi.getCases().execute()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.data)
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }

    override suspend fun getCase(id: String): Result<Case> {
        TODO("Not yet implemented")
    }

    override suspend fun addCase(case: Case): Result<Empty> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCase(case: Case): Result<Empty> {
        TODO("Not yet implemented")
    }

}