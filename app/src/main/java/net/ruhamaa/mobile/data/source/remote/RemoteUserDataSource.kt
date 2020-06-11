package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaApi
import net.ruhamaa.mobile.data.source.remote.requests.LoginRequest
import net.ruhamaa.mobile.data.source.remote.requests.VerifyRequest
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse
import net.ruhamaa.mobile.data.source.remote.responses.isSuccessful

class RemoteUserDataSource(private val ruhamaaApi: RuhamaaApi) : UserDataSource {

    override suspend fun login(phoneNum: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val request = LoginRequest(phoneNum, true) //TODO: remove agent later
            try {
                val response = ruhamaaApi.login(request).execute()
                if (response.isSuccessful) {
                    val body = response.body()!!
                    if (body.isSuccessful()) {
                        Result.Success(body.data)
                    } else {
                        Result.Error(Exception(body.msg))
                    }
                } else {
                    Result.Error(Exception("Request failed"))
                }
            } catch (e: Exception){
                Result.Error(Exception(e.localizedMessage))
            }
        }
    }

    override suspend fun verify(phoneNum: String, code: String): Result<User> {
        return withContext(Dispatchers.IO) {
            val request = VerifyRequest(phoneNum, code)
            val response = ruhamaaApi.verify(request).execute()
            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.isSuccessful()) {
                Result.Success(body.data)
                } else {
                    Result.Error(Exception(body.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }

    override fun getUser(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): Result<Empty> {
        TODO("Not yet implemented")
    }
}