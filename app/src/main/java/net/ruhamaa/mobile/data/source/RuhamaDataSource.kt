package net.ruhamaa.mobile.data.source

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User

interface RuhamaDataSource {
    suspend fun login(phoneNum: String): Result<Empty>

    suspend fun verify(phoneNum: String, code: String): Result<User>

    suspend fun forgotPassword(phoneNum: String): Result<Empty>

    suspend fun updateProfile(user: User): Result<User>

    suspend fun getCases(): Result<List<Case>>

    suspend fun addCase(case: Case): Result<Empty>

    suspend fun updateCase(case: Case): Result<Empty>
}