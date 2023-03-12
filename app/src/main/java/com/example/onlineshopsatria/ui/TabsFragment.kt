package com.example.onlineshopsatria.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHost
        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}