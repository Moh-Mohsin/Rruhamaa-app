package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User

// TODO: break into specific repositories based on use-cases
interface RuhamaaRepository {
    suspend fun login(phoneNum: String): Result<Empty>

    suspend fun verify(phoneNum: String, code: String): Result<User>

    suspend fun resendCode(phoneNum: String): Result<Empty>

    suspend fun forgotPassword(phoneNum: String): Result<Empty>

    suspend fun updateProfile(user: User): Result<User>

    suspend fun getCases(forceUpdate: Boolean = false): Result<List<Case>>

    suspend fun getCase(id: String, forceUpdate: Boolean = false): Result<Case>

    suspend fun addCase(case: Case): Result<Empty>

    suspend fun updateCase(case: Case): Result<Empty>
}