package net.ruhamaa.mobile.ui.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.ruhamaa.mobile.databinding.CaseListFragmentBinding
import net.ruhamaa.mobile.util.toast

class CaseListFragment : Fragment() {
    private var _binding: CaseListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CaseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CaseListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CaseListAdapter { case ->
            toast("$case")
        }

        binding.caseList.layoutManager = LinearLayoutManager(requireContext())
        binding.caseList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CaseListViewModel::class.java)
        viewModel.loadCases()
        viewModel.cases.observe(viewLifecycleOwner, Observer { cases ->
            adapter.submitList(cases)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
