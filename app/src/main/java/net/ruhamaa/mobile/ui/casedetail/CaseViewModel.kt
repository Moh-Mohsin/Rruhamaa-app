package net.ruhamaa.mobile.ui.casedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.GetCaseUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance
import java.lang.IllegalStateException

class CaseViewModel : ViewModel() {

    private val getCaseUseCase by KodeinInjector.instance<GetCaseUseCase>()

    private var _id: String? = null

    private val _case = MutableLiveData<Case>()
    val case = _case.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _message = MutableLiveData<Message>()
    val message = _message.toLive()

    fun setCaseId(id: String) {
        _id = id
        loadCase()
    }

    private fun loadCase(){
        viewModelScope.launch {
            _loading.value = true
            val result = _id?.let { getCaseUseCase(it) } ?: throw IllegalStateException("caseId not set")
            _loading.value = false
            when(result){
                is Result.Success -> {
                    _case.value = result.data
                }
                is Result.Error -> {
                    _message.value = Message.Raw(result.exception.toString())
                }
            }
        }
    }

}
