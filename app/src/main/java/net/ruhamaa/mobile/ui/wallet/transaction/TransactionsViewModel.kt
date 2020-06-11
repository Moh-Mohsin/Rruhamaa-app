package net.ruhamaa.mobile.ui.wallet.transaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Balance
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.GetBalanceUseCase
import net.ruhamaa.mobile.domain.GetTransactionsUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class TransactionsViewModel : ViewModel() {
    private val getTransactionsUseCase by KodeinInjector.instance<GetTransactionsUseCase>()

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions = _transactions.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _message = MutableLiveData<Event<Message>>()
    val message = _message.toLive()

    fun loadTransactions(){
        viewModelScope.launch {
            _loading.value = true
            val result = getTransactionsUseCase()
            _loading.value = false
            when(result){
                is Result.Success -> {
                    _transactions.value = result.data
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.message ?: result.exception.toString()))
                }
            }
        }
    }
}