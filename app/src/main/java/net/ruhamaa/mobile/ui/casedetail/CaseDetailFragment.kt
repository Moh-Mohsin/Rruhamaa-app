package net.ruhamaa.mobile.ui.casedetail

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.model.Image
import net.ruhamaa.mobile.databinding.FragmentCaseDetailBinding
import net.ruhamaa.mobile.ui.casedetail.adapter.ImageListAdapter
import java.util.*
import kotlin.math.roundToInt

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val isLeftToRight = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())==View.LAYOUT_DIRECTION_LTR
            if (isLeftToRight){
                binding.collapsingToolbar.collapsedTitleGravity = Gravity.LEFT
                binding.collapsingToolbar.expandedTitleGravity = Gravity.BOTTOM or Gravity.LEFT
            }
        }


        binding.collapsingToolbar.title = "Feeding a family of 15"
        val adapter = ImageListAdapter {

        }
        binding.imageList.adapter = adapter
        val linearLayoutManager =
            LinearLayoutManager(requireContext()).apply { orientation = RecyclerView.HORIZONTAL }
        binding.imageList.layoutManager = linearLayoutManager
        caseDetailViewModel.setCaseId(caseId)
        caseDetailViewModel.case.observe(viewLifecycleOwner, Observer { case ->
            binding.collapsingToolbar.title = case.title
            // TODO: format double (.2f)
            binding.donationProgress.current.text = getString(R.string.money_amount, case.currentDonations.toString())
            binding.donationProgress.target.text = getString(R.string.money_amount, case.targetDonations.toString())

            val remaining = case.targetDonations - case.currentDonations
            binding.remainingToTarget.text = getString(R.string.remaining_money_amount, remaining.toString())

            val progressPercent = ((case.currentDonations / case.targetDonations) * 100).roundToInt()
            binding.donationProgress.donationProgressBar.progress = progressPercent

            adapter.submitList(case.otherImages)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
