package net.ruhamaa.mobile.ui.wallet.deposit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image
import kotlinx.android.synthetic.main.deposit_fragment.*
import kotlinx.android.synthetic.main.deposit_fragment.view.*
import kotlinx.android.synthetic.main.deposit_fragment.view.loading
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.DepositFragmentBinding
import net.ruhamaa.mobile.util.getCompressed
import net.ruhamaa.mobile.util.hide
import net.ruhamaa.mobile.util.show
import net.ruhamaa.mobile.util.toast
import sd.nctr.covid19sudan.util.viewBinding
import java.io.File

class DepositFragment : Fragment(R.layout.deposit_fragment) {

    private val binding by viewBinding(DepositFragmentBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(DepositViewModel::class.java) }
    private var compressedImagePath: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imagePicker = ImagePicker.create(this)
            .returnMode(ReturnMode.ALL)
            .single()
        binding.addImage.setOnClickListener {
            imagePicker.start()
        }
        binding.imageName.setOnClickListener {
            compressedImagePath = null
            binding.imageName.hide()
            toast(R.string.image_removed)
        }
        binding.deposit.setOnClickListener {
            val amount = binding.amount.text.toString()
            val bankAccountNumber = binding.bankAccountNumber.text.toString()
            val bankBranch = binding.bankBranch.text.toString()
            val transactionNumber = binding.transactionNumber.text.toString()
            val notes = binding.notes.text.toString()

            if (amount.isBlank() || bankBranch.isBlank() || bankAccountNumber.isBlank() || transactionNumber.isBlank()) {
                toast(R.string.error_incomplete_fields)
            } else {
                viewModel.deposit(amount.toDouble(), bankAccountNumber, bankBranch, transactionNumber, compressedImagePath, notes)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                binding.loading.show()
                binding.content.hide()
            } else {
                binding.loading.hide()
                binding.content.show()
            }
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver { msg ->
            toast(msg.get(requireContext()))
        })
        viewModel.success.observe(viewLifecycleOwner, EventObserver { success ->
            if (success){
                val balance = viewModel.balance.value
                toast("success")
                toast(balance.toString())
                findNavController().popBackStack()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data) ?: kotlin.run {
                toast(R.string.error_no_image_selected)
                return
            }
            binding.imageName.text = image.name
            binding.imageName.show()

            val imageFile = File(image.path)
            val compressedImageFile: File = getCompressed(requireContext(), image.path)
            compressedImagePath = compressedImageFile.absolutePath

        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}