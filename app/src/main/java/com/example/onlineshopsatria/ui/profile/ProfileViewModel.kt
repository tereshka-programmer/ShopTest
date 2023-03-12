package com.example.onlineshopsatria.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopsatria.model.accounts.AccountsRepository
import com.example.onlineshopsatria.model.accounts.entities.Account
import com.example.onlineshopsatria.utils.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _profile = MutableLiveData<Account>()
    val profile = _profile.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _profile.value = it
            }
        }
    }

}