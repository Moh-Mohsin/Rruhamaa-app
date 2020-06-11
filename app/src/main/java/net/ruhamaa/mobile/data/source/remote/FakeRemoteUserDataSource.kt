package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.delay
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse

class FakeRemoteUserDataSource : UserDataSource {

    override suspend fun login(phoneNum: String): Result<LoginResponse> {
        delay(FakeRemoteDataSource.DELAY_IN_MS)
        return Result.Success(LoginResponse(phoneNum, "123456"))
    }

    override suspend fun verify(phoneNum: String, code: String): Result<User> {
        delay(FakeRemoteDataSource.DELAY_IN_MS)
        return Result.Success(User(1, "", phoneNum, 1))
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