package net.ruhamaa.mobile.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import net.ruhamaa.mobile.EventObserver
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.get
import net.ruhamaa.mobile.databinding.LoginFragmentBinding
import net.ruhamaa.mobile.util.toast

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = LoginFragmentBinding.bind(requireView())
        binding.login.setOnClickListener {
            viewModel.login(binding.phoneNum.text.toString())
        }
        viewModel.navigateToVerify.observe(viewLifecycleOwner, EventObserver {
            val action = LoginFragmentDirections.toVerifyFragment(it.phoneNum, it.otp)
            findNavController().navigate(action)
        })
        viewModel.message.observe(viewLifecycleOwner, EventObserver {
            toast(it.get(requireContext()))
        })
    }

}
