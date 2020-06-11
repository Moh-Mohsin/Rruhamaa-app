package net.ruhamaa.mobile.ui.casedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ruhamaa.mobile.Event
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.Message
import net.ruhamaa.mobile.data.Result
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Donation
import net.ruhamaa.mobile.di.KodeinInjector
import net.ruhamaa.mobile.domain.DonateUseCase
import net.ruhamaa.mobile.domain.GetCaseUseCase
import net.ruhamaa.mobile.util.toLive
import org.kodein.di.erased.instance

class CaseViewModel : ViewModel() {

    private val getCaseUseCase by KodeinInjector.instance<GetCaseUseCase>()
    private val donateUseCase by KodeinInjector.instance<DonateUseCase>()

    private var _id: Int? = null

    private val _case = MutableLiveData<Case>()
    val case = _case.toLive()

    private val _donation = MutableLiveData<Donation>()
    val donation = _donation.toLive()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.toLive()

    private val _loadingDonate = MutableLiveData<Boolean>()
    val loadingDonate = _loadingDonate.toLive()

    private val _message = MutableLiveData<Event<Message>>()
    val message = _message.toLive()

    private val _dialog = MutableLiveData<Event<Message>>()
    val dialog = _dialog.toLive()

    fun setCaseId(id: Int) {
        _id = id
        loadCase()
    }

    fun loadCase(){
        viewModelScope.launch {
            _loading.value = true
            val result = _id?.let { getCaseUseCase(it) } ?: throw IllegalStateException("caseId not set")
            _loading.value = false
            when(result){
                is Result.Success -> {
                    _case.value = result.data
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.message ?: result.exception.toString()))
                }
            }
        }
    }

    fun donate(amount: Double, notes: String){
        viewModelScope.launch {
            _loadingDonate.value = true
            val result = donateUseCase(_case.value!!, amount, notes)
            _loadingDonate.value = false
            when (result) {
                is Result.Success -> {
                    _donation.value = result.data
                    _dialog.value = Event(Message.Res(R.string.donation_successful))
                }
                is Result.Error -> {
                    _message.value = Event(Message.Raw(result.exception.message ?: result.exception.toString()))
                }
            }
        }
    }

}
