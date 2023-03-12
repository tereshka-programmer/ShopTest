package com.example.onlineshopsatria.ui.pageone

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.FragmentPageOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageOneFragment : Fragment(R.layout.fragment_page_one) {

    private lateinit var binding: FragmentPageOneBinding
    private lateinit var adapterFlash: FlashSaleAdapter
    private lateinit var adapterLatest: LatestAdapter
    private lateinit var adapterCategory: CategoryAdapter

    private val viewModel by viewModels<PageOneViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPageOneBinding.bind(view)

        adapterFlash = FlashSaleAdapter()
        adapterLatest = LatestAdapter()
        adapterCategory = CategoryAdapter(requireContext())

        val layoutManagerOne = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerTwo = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerThree = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rcvFlashSaleItems.adapter = adapterFlash
        binding.rcvFlashSaleItems.layoutManager = layoutManagerOne

        binding.rcvLatestItems.adapter = adapterLatest
        binding.rcvLatestItems.layoutManager = layoutManagerTwo

        binding.rcvCategoryItem.adapter = adapterCategory
        binding.rcvCategoryItem.layoutManager = layoutManagerThree

        viewModel.flashSalesList.observe(viewLifecycleOwner) {
            adapterFlash.flashSalesList = it
            Log.d("PASSWORDDDDDD", "FLASHSALEGET ${it.size}")
        }
        viewModel.latestList.observe(viewLifecycleOwner) {
            adapterLatest.listOfLatest = it
            Log.d("PASSWORDDDDDD", "LATEST ${it.size}")
        }

    }

}