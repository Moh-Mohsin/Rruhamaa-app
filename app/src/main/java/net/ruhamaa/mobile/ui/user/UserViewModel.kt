package net.ruhamaa.mobile.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.repsitory.UserRepository
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class UserViewModel: ViewModel() {
    val userRepository by KodeinInjector.instance<UserRepository>()
    private val _user = MutableLiveData<User?>()
    val user = _user.toLive()

    init {
        viewModelScope.launch {
            userRepository.getUserFlow().collect {
                _user.value = it
            }
        }
    }

    fun isLoggedIn() = _user.value != null

    fun logout(){
        viewModelScope.launch {
            userRepository.deleteUser()
        }
    }
    fun getUser() = userRepository.getUser()

}