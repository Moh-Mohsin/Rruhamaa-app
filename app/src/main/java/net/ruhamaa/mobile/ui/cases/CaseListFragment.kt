package net.ruhamaa.mobile.ui.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.MainActivity
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.CaseListFragmentBinding
import net.ruhamaa.mobile.util.toast
import sd.nctr.covid19sudan.util.viewBinding

class CaseListFragment : Fragment(R.layout.case_list_fragment) {


    private val binding by viewBinding(CaseListFragmentBinding::bind)

    private val viewModel by lazy { ViewModelProvider(this).get(CaseListViewModel::class.java) }
    private val userViewModel by lazy { (requireActivity() as MainActivity).userViewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userViewModel.getUser() == null) {
            val direction = CaseListFragmentDirections.toLoginFragment()
            findNavController().navigate(direction)
            return
        }
        val adapter = CaseListAdapter { case ->
            val direction = CaseListFragmentDirections.toCaseDetailFragment(case.id)
            findNavController().navigate(direction)
//            toast("$case")
        }

        binding.caseList.layoutManager = LinearLayoutManager(requireContext())
        binding.caseList.adapter = adapter

        viewModel.cases.observe(viewLifecycleOwner, Observer { cases ->
            adapter.submitList(cases)
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver {
            toast(it.get(requireContext()))
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCases()
    }
}
