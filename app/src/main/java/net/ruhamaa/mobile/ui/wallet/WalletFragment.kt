package net.ruhamaa.mobile.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.databinding.FragmentWalletBinding
import net.ruhamaa.mobile.ui.wallet.adapter.TabAdapter
import net.ruhamaa.mobile.ui.wallet.balance.BalanceFragment
import net.ruhamaa.mobile.ui.wallet.transaction.TransactionsFragment
import sd.nctr.covid19sudan.util.viewBinding

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private val binding by viewBinding(FragmentWalletBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(BalanceFragment(), getString(R.string.balance))
        adapter.addFragment(TransactionsFragment(), getString(R.string.transactions))

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.deposit.setOnClickListener {
            val action = WalletFragmentDirections.toDepositFragment()
            findNavController().navigate(action)
        }
    }
}