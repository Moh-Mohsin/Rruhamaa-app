package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.repsitory.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(phoneNum: String): Result<Empty> {
        wrapEspressoIdlingResource {
            return userRepository.login(phoneNum)
        }
    }

}