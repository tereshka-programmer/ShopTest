package com.example.onlineshopsatria.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.FragmentLoginPageBinding
import com.example.onlineshopsatria.ui.signup.SignInPageViewModel
import com.example.onlineshopsatria.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPageFragment : Fragment(R.layout.fragment_login_page) {

    private lateinit var binding: FragmentLoginPageBinding

    private val viewModel by viewModels<LoginPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginPageBinding.bind(view)

        binding.btSignIn.setOnClickListener {
            onLoginButtonPressed()
        }

        observeState()
        observeClearPasswordEvent()
        observeShowAuthErrorMessageEvent()
        observeNavigateToTabsEvent()
    }

    private fun onLoginButtonPressed() {
        viewModel.login(
            firstName = binding.edtFirstName.text.toString(),
            password = binding.edtPassword.text.toString()
        )
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        if (it.emptyFirstNameError) fillError(R.string.field_is_empty)
        if (it.emptyPasswordError) fillError(R.string.field_is_empty)

        binding.edtFirstName.isEnabled = it.enableViews
        binding.edtPassword.isEnabled = it.enableViews
        binding.btSignIn.isEnabled = it.enableViews
        binding.progressBar.visibility = if (it.showProgress) View.VISIBLE else View.INVISIBLE
    }

    private fun fillError(@StringRes stringRes: Int) {
        if (stringRes != SignInPageViewModel.NO_ERROR_MESSAGE) {
            Toast.makeText(requireContext(), stringRes, Toast.LENGTH_LONG).show()
        }
    }

    private fun observeShowAuthErrorMessageEvent() = viewModel.showAuthToastEvent.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun observeClearPasswordEvent() = viewModel.clearPasswordEvent.observeEvent(viewLifecycleOwner) {
        binding.edtPassword.text?.clear()
    }

    private fun observeNavigateToTabsEvent() = viewModel.navigateToTabsEvent.observeEvent(viewLifecycleOwner) {
        val action = LoginPageFragmentDirections.actionLoginPageFragmentToTabsFragment()
        findNavController().navigate(action)
    }

}