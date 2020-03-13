package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty

class LoginUseCase(
    private val ruhamaaRepository: RuhamaaRepository
) {
    suspend operator fun invoke(phoneNum: String): Result<Empty> {
        wrapEspressoIdlingResource {
            return ruhamaaRepository.login(phoneNum)
        }
    }

}