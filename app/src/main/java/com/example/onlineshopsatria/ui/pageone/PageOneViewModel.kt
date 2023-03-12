package com.example.onlineshopsatria.ui.pageone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.network_impl.products.ProductsSource
import com.example.network.network_impl.products.entities.FlashSaleItem
import com.example.network.network_impl.products.entities.LatestItem
import com.example.onlineshopsatria.utils.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageOneViewModel @Inject constructor(
    private val productsSource: ProductsSource
) : ViewModel() {

    private val _flashSalesList = MutableLiveData<List<FlashSaleItem>>()
    val flashSalesList = _flashSalesList.share()

    private val _latestList = MutableLiveData<List<LatestItem>>()
    val latestList = _latestList.share()

    init {
        orderFlashSales()
        orderLatest()
    }

    fun orderFlashSales() {
        viewModelScope.launch {
            try {
                _flashSalesList.value = productsSource.getAllFlashSaleItems()
            } catch (e: Exception) {

            }
        }
    }

    fun orderLatest() {
        viewModelScope.launch {
            try {
                _latestList.value = productsSource.getAllLatestItems()
            } catch (e: Exception) {

            }
        }
    }
}