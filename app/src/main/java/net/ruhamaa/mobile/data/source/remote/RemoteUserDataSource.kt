package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.delay
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.UserDataSource

class RemoteUserDataSource : UserDataSource {

    override suspend fun login(phoneNum: String): Result.Success<Empty> {
        delay(FakeRemoteDataSource.DELAY_IN_MS)
        return successEmpty()
    }

    override suspend fun verify(phoneNum: String, code: String): Result.Success<User> {
        delay(FakeRemoteDataSource.DELAY_IN_MS)
        return Result.Success(User(phoneNum))
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