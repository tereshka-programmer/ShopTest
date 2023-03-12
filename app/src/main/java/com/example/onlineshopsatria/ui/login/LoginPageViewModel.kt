package com.example.onlineshopsatria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.model.AuthException
import com.example.onlineshopsatria.model.EmptyFieldException
import com.example.onlineshopsatria.model.Field
import com.example.onlineshopsatria.model.StorageException
import com.example.onlineshopsatria.model.accounts.AccountsRepository
import com.example.onlineshopsatria.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _clearPasswordEvent = MutableUnitLiveEvent()
    val clearPasswordEvent = _clearPasswordEvent.share()

    private val _showAuthErrorToastEvent = MutableLiveEvent<Int>()
    val showAuthToastEvent = _showAuthErrorToastEvent.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun login(firstName: String, password: String) = viewModelScope.launch {
        showProgress()
        try {
            Log.d("PASSWORDDDDDD", "ENTRYPOINT")
            accountsRepository.logIn(firstName, password)
            launchTabsScreen()
        } catch (e: EmptyFieldException) {
            processEmptyFieldException(e)
        } catch (e: AuthException) {
            processAuthException()
        } catch (e: StorageException) {
            processStorageException()
        }
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = _state.requireValue().copy(
            emptyFirstNameError = e.field == Field.Email,
            emptyPasswordError = e.field == Field.Password,
            signInInProgress = false
        )
    }

    private fun processAuthException() {
        _state.value = _state.requireValue().copy(
            signInInProgress = false
        )
        clearPasswordField()
        showAuthErrorToast()
    }

    private fun processStorageException() {
        _showAuthErrorToastEvent.publishEvent(R.string.storage_error)
        _state.value = _state.requireValue().copy(
            signInInProgress = false
        )
    }

    private fun showProgress() {
        _state.value = State(signInInProgress = true)
    }

    private fun clearPasswordField() = _clearPasswordEvent.publishEvent()

    private fun showAuthErrorToast() = _showAuthErrorToastEvent.publishEvent(R.string.invalid_first_or_password)

    private fun launchTabsScreen() = _navigateToTabsEvent.publishEvent()

    data class State(
        val emptyFirstNameError: Boolean = false,
        val emptyPasswordError: Boolean = false,
        val signInInProgress: Boolean = false
    ) {
        val showProgress: Boolean get() = signInInProgress
        val enableViews: Boolean get() = !signInInProgress
    }
}