package net.ruhamaa.mobile.ui.casedetail

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.api.load
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.deposit_fragment.*
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.FragmentCaseDetailBinding
import net.ruhamaa.mobile.databinding.PaymentDialogBinding
import net.ruhamaa.mobile.ui.casedetail.adapter.ImageListAdapter
import net.ruhamaa.mobile.util.hide
import net.ruhamaa.mobile.util.show
import net.ruhamaa.mobile.util.toast
import sd.nctr.covid19sudan.util.viewBinding
import java.util.*
import kotlin.math.roundToInt

/**
 * A simple [Fragment] subclass.
 */
class CaseDetailFragment : Fragment(R.layout.fragment_case_detail) {

    private val binding by viewBinding(FragmentCaseDetailBinding::bind)

    private val viewModel by lazy { ViewModelProvider(this).get(CaseViewModel::class.java) }

    private val alertDialog by lazy { createPaymentDialog() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val caseId = CaseDetailFragmentArgs.fromBundle(requireArguments()).caseId

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val isLeftToRight =
                TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_LTR
            if (isLeftToRight) {
                binding.collapsingToolbar.collapsedTitleGravity = Gravity.LEFT
                binding.collapsingToolbar.expandedTitleGravity = Gravity.BOTTOM or Gravity.LEFT
            }
        }


        binding.collapsingToolbar.apply {
            setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
            setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        }
        val adapter = ImageListAdapter {

        }
        binding.imageList.adapter = adapter
        val linearLayoutManager =
            LinearLayoutManager(requireContext()).apply { orientation = RecyclerView.HORIZONTAL }
        binding.imageList.layoutManager = linearLayoutManager
        viewModel.setCaseId(caseId)
        viewModel.case.observe(viewLifecycleOwner, Observer { case ->
            binding.image.load(case.imgUrl)
            binding.collapsingToolbar.title = case.title
            binding.caseDescription.text = case.description
            // TODO: format double (.2f)
            binding.donationProgress.current.text =
                getString(R.string.money_amount, case.currentDonations.toString())
            binding.donationProgress.target.text =
                getString(R.string.money_amount, case.targetDonations.toString())

            var remaining = case.targetDonations - case.currentDonations
            if (remaining < 0) remaining = 0.0
            binding.remainingToTarget.text =
                getString(R.string.remaining_money_amount, remaining.toString())

            val progressPercent =
                ((case.currentDonations / case.targetDonations) * 100).roundToInt()
            binding.donationProgress.donationProgressBar.progress = progressPercent

            adapter.submitList(case.otherImages)
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver { msg ->
            toast(msg.get(requireContext()))
        })
        viewModel.loadingDonate.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                binding.donateLoading.show()
                binding.donateButton.hide()
            } else {
                binding.donateLoading.hide()
                binding.donateButton.show()
            }
        })

        viewModel.dialog.observe(viewLifecycleOwner, EventObserver { msg ->
            SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setContentText(msg.get(requireContext()))
                .setConfirmButton(R.string.ok) { sDialog ->
                    sDialog.dismissWithAnimation()
                }
                .show()
        })

        binding.donateButton.setOnClickListener {
            alertDialog.show()
        }
    }

    private fun createPaymentDialog(): AlertDialog {
        val dialogView = layoutInflater.inflate(R.layout.payment_dialog, null)
        val paymentDialogBinding = PaymentDialogBinding.bind(dialogView)
        return MaterialAlertDialogBuilder(requireContext())
            .setView(paymentDialogBinding.root)
            .setPositiveButton(R.string.donate) { dialog, _ ->
                val amount = paymentDialogBinding.amount.text.toString().toDoubleOrNull()
                val notes = paymentDialogBinding.notes.text.toString()
                if (amount == null) {
                    toast(R.string.error_invalid_amount)
                } else {
                    viewModel.donate(amount, notes)
                    dialog.dismiss()
                }
            }.create()
    }

}
