package net.ruhamaa.mobile.ui.wallet.transaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.TransactionsFragmentBinding
import net.ruhamaa.mobile.util.hide
import net.ruhamaa.mobile.util.show
import net.ruhamaa.mobile.util.toast
import sd.nctr.covid19sudan.util.viewBinding

class TransactionsFragment : Fragment(R.layout.transactions_fragment) {

    private val binding by viewBinding(TransactionsFragmentBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(TransactionsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionListAdapter()
        binding.transactionList.layoutManager = LinearLayoutManager(requireContext())
        binding.transactionList.adapter = adapter

        viewModel.loadTransactions()

        viewModel.transactions.observe(viewLifecycleOwner, Observer { transactions ->
            binding.transactionList.show()
            adapter.submitList(transactions.reversed())
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                binding.loading.show()
                binding.transactionList.hide()
            } else {
                binding.loading.hide()
            }
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver {
            toast(it.get(requireContext()))
        })
    }
}