package com.example.onlineshopsatria.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.onlineshopsatria.R

fun Fragment.findTopNavController(): NavController {
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}