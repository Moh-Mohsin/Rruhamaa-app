package net.ruhamaa.mobile.ui.wallet.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.BalanceFragmentBinding
import net.ruhamaa.mobile.util.toast
import sd.nctr.covid19sudan.util.viewBinding

class BalanceFragment : Fragment(R.layout.balance_fragment) {

    private val binding by viewBinding(BalanceFragmentBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(BalanceViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadBalance()
        viewModel.balance.observe(viewLifecycleOwner, Observer { balance ->
            binding.balance.text = balance.value.toString()
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver {
            toast(it.get(requireContext()))
        })
    }
}