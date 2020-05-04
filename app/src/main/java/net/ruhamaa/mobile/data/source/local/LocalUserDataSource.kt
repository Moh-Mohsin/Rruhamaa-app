package net.ruhamaa.mobile.data.source.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Empty
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.remote.responses.LoginResponse
import net.ruhamaa.mobile.data.toSuccess
import java.lang.Exception

class LocalUserDataSource(private val preferences: SharedPreferences) : UserDataSource {
    private val gson by lazy { Gson() }
    private val userType = object :
        TypeToken<User>() {}.type

    override suspend fun login(phoneNum: String): Result<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verify(phoneNum: String, code: String): Result<User> {
        TODO("Not yet implemented")
    }

    override fun getUser(): Result<User> {
        val user = preferences.getString(USER, null)?.let {
            gson.fromJson<User>(it, userType)
        }
        return user?.toSuccess() ?: Result.Error(Exception())
    }

    override suspend fun updateUser(user: User): Result<User> {
        val userString = gson.toJson(user)
        preferences.edit {
            putString(USER, userString)
        }
        return user.toSuccess()
    }

    override suspend fun deleteUser(): Result<Empty> {
        preferences.edit{
            remove(USER)
        }
        return Empty().toSuccess()
    }

    companion object {
        private const val USER = "USER"
    }
}