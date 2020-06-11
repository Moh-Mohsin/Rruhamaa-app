package net.ruhamaa.mobile.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.repsitory.UserRepository
import net.ruhamaa.mobile.data.require
import net.ruhamaa.mobile.data.source.DonationDataSource
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.dto.DonationDto
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaApi
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest
import net.ruhamaa.mobile.data.source.remote.responses.isSuccessful
import net.ruhamaa.mobile.util.toDonateDto

class RemoteDonationDataSource(
    private val ruhamaaApi: RuhamaaApi,
    private val userRepository: UserRepository
) : DonationDataSource {
    override suspend fun donate(donationRequest: DonationRequest): Result<DonationDto> {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()!!
            val response = ruhamaaApi.donate(donationRequest.toDonateDto(user)).execute()
            if (response.isSuccessful) {
                val result = response.body()!!
                if (result.isSuccessful()) {
                    Result.Success(result.data)
                } else {
                    Result.Error(Exception(result.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }

    override suspend fun getDonations(): Result<List<DonationDto>> {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()!!
            val response = ruhamaaApi.getDonations(user.id).execute()
            if (response.isSuccessful) {
                val result = response.body()!!
                if (result.isSuccessful()) {
                    Result.Success(result.data)
                } else {
                    Result.Error(Exception(result.msg))
                }
            } else {
                Result.Error(Exception("Request failed"))
            }
        }
    }
}