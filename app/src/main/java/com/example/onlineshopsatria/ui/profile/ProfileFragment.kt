package com.example.onlineshopsatria.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.FragmentProfilePageBinding
import com.example.onlineshopsatria.ui.TabsFragmentDirections
import com.example.onlineshopsatria.utils.findTopNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile_page) {

    private lateinit var binding: FragmentProfilePageBinding

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfilePageBinding.bind(view)

        binding.btLogout.setOnClickListener {
            onLogoutButtonClick()
        }

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.tvNameOfCustomer.text = "${it.lastName} ${it.firstName}"
        }
    }

    private fun onLogoutButtonClick() {
        val action = TabsFragmentDirections.actionTabsFragmentToSignInPageFragment()
        val navOptions = NavOptions.Builder().setPopUpTo(findTopNavController().graph.startDestinationId, true).build()
        findTopNavController().navigate(action, navOptions)
    }
}