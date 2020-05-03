package net.ruhamaa.mobile.ui.casedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ruhamaa.mobile.data.model.Image
import net.ruhamaa.mobile.databinding.FragmentCaseDetailBinding
import net.ruhamaa.mobile.ui.casedetail.adapter.ImageListAdapter

/**
 * A simple [Fragment] subclass.
 */
class CaseDetailFragment : Fragment() {

    private var _binding: FragmentCaseDetailBinding? = null
    private val binding get() = _binding!!

    private val caseDetailViewModel by lazy { ViewModelProvider(this).get(CaseViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCaseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val caseId = CaseDetailFragmentArgs.fromBundle(requireArguments()).caseId
        val adapter = ImageListAdapter {

        }
        binding.imageList.adapter = adapter
        val linearLayoutManager =
            LinearLayoutManager(requireContext()).apply { orientation = RecyclerView.HORIZONTAL }
        binding.imageList.layoutManager = linearLayoutManager
        caseDetailViewModel.setCaseId(caseId)
        caseDetailViewModel.case.observe(viewLifecycleOwner, Observer { case ->
            adapter.submitList(case.otherImages)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
