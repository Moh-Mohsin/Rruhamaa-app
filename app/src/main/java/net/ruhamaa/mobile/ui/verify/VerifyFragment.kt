package net.ruhamaa.mobile.ui.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.databinding.VerifyFragmentBinding
import net.ruhamaa.mobile.util.toast

class VerifyFragment : Fragment() {

    private val viewModel by viewModels<VerifyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.verify_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = VerifyFragmentBinding.bind(requireView())
        val phoneNum = VerifyFragmentArgs.fromBundle(requireArguments()).phoneNum
        binding.verify.setOnClickListener {
            viewModel.verify(phoneNum, binding.code.text.toString())
        }
        viewModel.navigateToMain.observe(viewLifecycleOwner, EventObserver {
            toast("Logged in! $it")
            val action = VerifyFragmentDirections.toCaseListFragment()
            findNavController().navigate(action)
        })
    }

}
