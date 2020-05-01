package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.delay
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.toSuccess
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.source.RuhamaDataSource

class FakeRemoteDataSource : RuhamaDataSource {

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

fun fakeCase() = fakeCases().single()

fun fakeCases() = genFakeCase2()

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


fun genFakeCase2() = listOf(
    Case(
        "1",
        "case 1",
        "Feed family of 15",
        "1/1/2020",
        true,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        50,
        230,
        4000.0,
        2430.0
    ),
    Case(
        "2",
        "case 2",
        "Feed family of 15",
        "10/3/2020",
        false,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        8,
        50,
        500.0,
        130.0
    ),
    Case(
        "3",
        "case 3",
        "Feed family of 15",
        "1/1/2020",
        false,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        30,
        68,
        1650.0,
        1500.0
    ),
    Case(
        "3",
        "case 4",
        "Feed family of 15",
        "1/2/2020",
        false,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        1,
        5,
        850.0,
        50.0
    ),
    Case(
        "5",
        "case 5",
        "Feed family of 15",
        "21/1/2020",
        false,
        "https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg",
        5,
        40,
        1650.0,
        500.0
    )
)