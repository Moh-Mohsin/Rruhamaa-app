package net.ruhamaa.mobile.data.repsitory

import kotlinx.coroutines.flow.Flow
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User

// TODO: break into specific repositories based on use-cases
interface UserRepository {
    suspend fun login(phoneNum: String): Result<Empty>

    suspend fun verify(phoneNum: String, code: String): Result<User>

    suspend fun resendCode(phoneNum: String): Result<Empty>



    suspend fun forgotPassword(phoneNum: String): Result<Empty>

    suspend fun updateProfile(user: User): Result<User>

    suspend fun getUserFlow(): Flow<User?>

    fun getUser(): User?

    suspend fun updateUser(user: User): Result<User>

    suspend fun deleteUser(): Result<Empty>
}