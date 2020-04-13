package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.delay
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.toSuccess
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.source.RuhamaDataSource

class FakeRemoteDataSource : RuhamaDataSource {
    override suspend fun login(phoneNum: String): Result.Success<Empty> {
        delay(DELAY_IN_MS)
        return successEmpty()
    }

    override suspend fun verify(phoneNum: String, code: String): Result.Success<User> {
        delay(DELAY_IN_MS)
        return Result.Success(User(phoneNum))
    }

    override suspend fun forgotPassword(phoneNum: String): Result.Success<Empty> {
        delay(DELAY_IN_MS)
        return Result.Success(Empty())
    }

    override suspend fun updateProfile(user: User): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCases() = fakeCases().toSuccess()

    override suspend fun getCase(id: String) = fakeCases().single { it.id == id }.toSuccess()

    override suspend fun addCase(case: Case) = Result.Success(Empty())

    override suspend fun updateCase(case: Case) = successEmpty()

    companion object {
        const val DELAY_IN_MS = 500L
    }
}

fun successEmpty() = Result.Success(Empty())

fun fakeUser() = User("+999123456789")

fun fakeCase() = genFakeCase(1).single()

fun fakeCases() = genFakeCase(5)

fun genFakeCase(n: Int) = (1..n).map {
    Case(
        "$it",
        "case $it",
        "des",
        "1/1/2020",
        it % 2 == 0,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        it * 2,
        it * 4,
        it * 250.0,
        it * 2 * 100.0
    )
}