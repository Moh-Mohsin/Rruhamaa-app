package net.ruhamaa.mobile.domain

import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.util.wrapEspressoIdlingResource
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty

class GetCasesUseCase(
    private val ruhamaaRepository: RuhamaaRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<List<Case>> {
        wrapEspressoIdlingResource {
            return ruhamaaRepository.getCases(forceUpdate)
        }
    }

}