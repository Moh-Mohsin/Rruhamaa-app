package net.ruhamaa.mobile.data.repsitory

import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.*
import java.time.temporal.TemporalAmount

interface DonationRepository {

    suspend fun donate(case: Case, amount: Double, notes: String): Result<Donation>

    suspend fun getDonations(forceUpdate: Boolean): Result<List<Donation>>
}