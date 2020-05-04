package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.repsitory.UserRepository
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(phoneNum: String): Result<LoginResponse> {
        wrapEspressoIdlingResource {
            return userRepository.login(phoneNum)
        }
    }

}