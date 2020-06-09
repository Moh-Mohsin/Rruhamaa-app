package net.ruhamaa.mobile.ui.wallet.deposit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Credit
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.DepositUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class DepositViewModel : ViewModel() {


    private val depositUseCase by KodeinInjector.instance<DepositUseCase>()

    private val _balance = MutableLiveData<Balance>()
    val balance = _balance.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _success = MutableLiveData<Event<Boolean>>()
    val success = _success.toLive()

    private val _message = MutableLiveData<Event<Message>>()
    val message = _message.toLive()

    fun deposit(
        amount: Double,
        bankAccount: String,
        bankBranch: String,
        transNo: String,
        imgUrl: String?,
        notes: String?
    ) {
        viewModelScope.launch {
            _loading.value = true
            val credit = Credit(amount, bankAccount, bankBranch, transNo, imgUrl, notes)
            val result = depositUseCase(credit)
            _loading.value = false
            when (result) {
                is Result.Success -> {
                    _balance.value = result.data
                    _success.value = Event(true)
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.toString()))
                }
            }
        }
    }
}