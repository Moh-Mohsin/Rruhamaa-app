package net.ruhamaa.mobile.data.source.remote.network

import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.dto.CreditDto
import net.ruhamaa.mobile.data.source.dto.DonateDto
import net.ruhamaa.mobile.data.source.dto.DonationDto
import net.ruhamaa.mobile.data.source.dto.TransactionDto
import net.ruhamaa.mobile.data.source.remote.requests.LoginRequest
import net.ruhamaa.mobile.data.source.remote.requests.VerifyRequest
import net.ruhamaa.mobile.data.source.remote.responses.BaseResponse
import net.ruhamaa.mobile.data.source.remote.responses.BaseResponseList
import net.ruhamaa.mobile.data.source.remote.responses.GetBalanceResponse
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RuhamaaApi {

    @POST("register")
    fun login(
        @Body
        loginRequest: LoginRequest
    ): Call<BaseResponse<LoginResponse>>


    @POST("verifyAndLogin")
    fun verify(
        @Body
        verifyRequest: VerifyRequest
    ): Call<BaseResponse<User>>


    @GET("case/all")
    fun getCases(): Call<BaseResponseList<Case>>

    // Wallet

    @GET("wallet/getBalance/{wallet_id}")
    fun getBalance(
        @Path("wallet_id")
        walletId: Int
    ): Call<GetBalanceResponse>


    @POST("wallet/credit")
    fun credit(
        @Body
        creditDto: CreditDto
    ): Call<BaseResponse<CreditDto>>


    @GET("wallet/getTransactions/{wallet_id}")
    fun getTransactions(
        @Path("wallet_id")
        walletId: Int
    ): Call<BaseResponseList<TransactionDto>>

    // Donation

    @POST("donation/donate")
    fun donate(
        @Body
        donateDto: DonateDto
    ): Call<BaseResponse<DonationDto>>

    @GET("donation/all/{user_id}")
    fun getDonations(
        @Path("user_id")
        userId: Int
    ): Call<BaseResponseList<DonationDto>>
}