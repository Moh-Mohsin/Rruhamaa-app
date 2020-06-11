package net.ruhamaa.mobile.data.source.remote.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import net.ruhamaa.mobile.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RuhamaaService {


    fun getService(): RuhamaaApi =
            getRetrofit(BASE_URL).create(RuhamaaApi::class.java)

    private fun getRetrofit(baseUrl: String): Retrofit {

        val timeout = 30L
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            // Log only on debug builds
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(logging)
            }
        }
        return Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .baseUrl(baseUrl)
                .build()
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder().build()
    }
    private fun getGson() =
        GsonBuilder()
//            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    companion object {
        const val BASE_URL = "http://ruhama.cleverapps.io/ruhama/api/"

    }
}


