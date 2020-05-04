package net.ruhamaa.mobile.data.source.remote.network

import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.remote.requests.LoginRequest
import net.ruhamaa.mobile.data.source.remote.requests.VerifyRequest
import net.ruhamaa.mobile.data.source.remote.responses.BaseResponse
import net.ruhamaa.mobile.data.source.remote.responses.BaseResponseList
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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


}