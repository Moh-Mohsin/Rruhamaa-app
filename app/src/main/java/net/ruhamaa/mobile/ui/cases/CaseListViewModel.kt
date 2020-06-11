package net.ruhamaa.mobile.ui.cases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.GetCasesUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class CaseListViewModel : ViewModel() {

    private val getCasesUseCase by KodeinInjector.instance<GetCasesUseCase>()

    private val _cases = MutableLiveData<List<Case>>()
    val cases = _cases.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _message = MutableLiveData<Event<Message>>()
    val message = _message.toLive()

    fun loadCases(){
        viewModelScope.launch {
            _loading.value = true
            val result = getCasesUseCase()
            _loading.value = false
            when(result){
                is Result.Success -> {
                    _cases.value = result.data
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.message ?: result.exception.toString()))
                }
            }
        }
    }

}
