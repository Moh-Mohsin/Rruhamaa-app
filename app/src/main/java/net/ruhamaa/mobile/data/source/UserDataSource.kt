package net.ruhamaa.mobile.data.source

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User

// TODO: break into specific repositories based on use-cases
interface UserDataSource {
    suspend fun login(phoneNum: String): Result<Empty>

    suspend fun verify(phoneNum: String, code: String): Result<User>

    fun getUser(): Result<User>

    suspend fun updateUser(user: User): Result<User>

    suspend fun deleteUser(): Result<Empty>
}