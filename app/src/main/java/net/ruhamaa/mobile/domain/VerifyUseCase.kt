package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result

class VerifyUseCase(
    private val ruhamaaRepository: RuhamaaRepository
) {
    suspend operator fun invoke(phoneNum: String, code: String): Result<User> {
        wrapEspressoIdlingResource {
            return ruhamaaRepository.verify(phoneNum, code)
        }
    }

}