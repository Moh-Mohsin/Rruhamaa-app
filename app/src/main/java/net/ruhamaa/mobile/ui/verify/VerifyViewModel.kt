package net.ruhamaa.mobile.ui.verify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.data.ErrorMessage
import net.ruhamaa.mobile.data.Result.*
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.toErrorMessage
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.VerifyUseCase
import net.ruhamaa.mobile.util.readOnly
import org.kodein.di.erased.instance

class VerifyViewModel : ViewModel() {
    private val verifyUseCase by KodeinInjector.instance<VerifyUseCase>()

    private val _user = MutableLiveData<User>()
    val user = _user.readOnly()

    private val _navigateToMain = MutableLiveData<Event<User>>()
    val navigateToMain = _navigateToMain.readOnly()

    private val _snackbarText = MutableLiveData<Event<ErrorMessage>>()
    val snackbarText = _snackbarText.readOnly()

    fun verify(phoneNum: String, code: String){
        viewModelScope.launch {
            when(val result = verifyUseCase(phoneNum, code)){
                is Success -> {
                    _navigateToMain.value = Event(result.data)
                    _user.value = result.data
                }
                is Error -> {
                    _snackbarText.value = Event(result.toErrorMessage())
                }
                Loading -> {}
            }
        }
    }
}
