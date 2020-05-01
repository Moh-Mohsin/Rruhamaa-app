package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.RuhamaDataSource
class RuhamaaRepositoryImpl(private val ruhamaDataSource: RuhamaDataSource) : RuhamaaRepository {

    override suspend fun getCases(forceUpdate: Boolean): Result<List<Case>> {
        return ruhamaDataSource.getCases()
    }

    override suspend fun getCase(id: String, forceUpdate: Boolean): Result<Case> {
        return ruhamaDataSource.getCase(id)
    }

    override suspend fun addCase(case: Case): Result<Empty> {
        return ruhamaDataSource.addCase(case)
    }

    override suspend fun updateCase(case: Case): Result<Empty> {
        return ruhamaDataSource.updateCase(case)
    }
}