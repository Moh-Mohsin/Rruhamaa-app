package net.ruhamaa.mobile.ui.wallet.balance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.GetBalanceUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class BalanceViewModel : ViewModel() {
    private val getBalanceUseCase by KodeinInjector.instance<GetBalanceUseCase>()

    private val _balance = MutableLiveData<Balance>()
    val balance = _balance.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _message = MutableLiveData<Event<Message>>()
    val message = _message.toLive()

    fun loadBalance(){
        viewModelScope.launch {
            _loading.value = true
            val result = getBalanceUseCase()
            _loading.value = false
            when(result){
                is Result.Success -> {
                    _balance.value = result.data
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.toString()))
                }
            }
        }
    }
}