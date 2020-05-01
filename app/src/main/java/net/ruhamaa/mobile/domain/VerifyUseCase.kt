package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.repsitory.UserRepository

class VerifyUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(phoneNum: String, code: String): Result<User> {
        wrapEspressoIdlingResource {
            return userRepository.verify(phoneNum, code)
        }
    }

}