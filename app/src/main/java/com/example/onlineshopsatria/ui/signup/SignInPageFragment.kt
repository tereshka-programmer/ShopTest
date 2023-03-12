package com.example.onlineshopsatria.ui.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.FragmentSignInPageBinding
import com.example.onlineshopsatria.model.accounts.entities.SignUpData
import com.example.onlineshopsatria.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInPageFragment : Fragment(R.layout.fragment_sign_in_page) {

    private lateinit var binding: FragmentSignInPageBinding

    private val viewModel by viewModels<SignInPageViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInPageBinding.bind(view)

        binding.btSignIn.setOnClickListener {
            onCreateAccountButtonPressed()
        }
        binding.tvbLogin.setOnClickListener {
            val action = SignInPageFragmentDirections.actionSignInPageFragmentToLoginPageFragment()
            findNavController().navigate(action)
        }


        observeState()
        observeGoToLoginEvent()
        observeShowSuccessSignUpMessageEvent()
    }

    private fun onCreateAccountButtonPressed() {
        val signUpData = SignUpData(
            email = binding.edtEmail.text.toString(),
            firstName = binding.edtFirstName.text.toString(),
            lastName = binding.edtLastName.text.toString()
        )
        viewModel.sighUp(signUpData)
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) { state ->
        binding.btSignIn.isEnabled = state.enableViews
        binding.edtEmail.isEnabled = state.enableViews
        binding.edtFirstName.isEnabled = state.enableViews
        binding.edtLastName.isEnabled = state.enableViews

        fillError(state.emailErrorMessageRes)
        fillError(state.emailMismatchErrorMessageRes)
        fillError(state.firstNameErrorMessageRes)
        fillError(state.lastNameErrorMessageRes)

        binding.progressBar.visibility = if (state.showProgress) View.VISIBLE else View.INVISIBLE
    }

    private fun fillError(@StringRes stringRes: Int) {
        if (stringRes != SignInPageViewModel.NO_ERROR_MESSAGE) {
            Toast.makeText(requireContext(), stringRes, Toast.LENGTH_LONG).show()
        }
    }

    private fun observeShowSuccessSignUpMessageEvent() = viewModel.showToastEvent.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private fun observeGoToLoginEvent() = viewModel.goBackEvent.observeEvent(viewLifecycleOwner) {
        val action = SignInPageFragmentDirections.actionSignInPageFragmentToLoginPageFragment()
        findNavController().navigate(action)
    }

}