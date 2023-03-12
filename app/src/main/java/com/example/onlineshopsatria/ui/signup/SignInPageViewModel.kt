package com.example.onlineshopsatria.ui.signup

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.model.*
import com.example.onlineshopsatria.model.accounts.AccountsRepository
import com.example.onlineshopsatria.model.accounts.entities.SignUpData
import com.example.onlineshopsatria.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInPageViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository,
) : ViewModel() {

    private val _goBackEvent = MutableUnitLiveEvent()
    val goBackEvent = _goBackEvent.share()

    private val _showToastEvent = MutableLiveEvent<Int>()
    val showToastEvent = _showToastEvent.share()

    private val _state = MutableLiveData(State())
    val state = _state.share()


    fun sighUp(signUpData: SignUpData) {
        viewModelScope.launch {
            showProgress()
            try {
                accountsRepository.signUp(signUpData)
                showSuccessSignUpMessage()
                goBack()
            } catch (e: EmptyFieldException) {
                processEmptyFieldException(e)
            } catch (e: AccountAlreadyExistsException) {
                processAccountAlreadyExistsException()
            } catch (e: EmailMismatchException) {
                processEmailMismatch()
            } catch (e: StorageException) {
                processStorageException()
            } finally {
                hideProgress()
            }
        }
    }

    private fun processEmailMismatch() {
        _state.value = _state.requireValue().copy(emailErrorMessageRes = R.string.email_is_mismatch)
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = when(e.field) {
            Field.Email -> _state.requireValue()
                .copy(emailErrorMessageRes = R.string.field_is_empty)
            Field.Firstname -> _state.requireValue()
                .copy(firstNameErrorMessageRes = R.string.field_is_empty)
            Field.Lastname -> _state.requireValue()
                .copy(lastNameErrorMessageRes = R.string.field_is_empty)
            else -> throw IllegalStateException("Unknown field")
        }
    }

    private fun processAccountAlreadyExistsException() {
        _state.value = _state.requireValue()
            .copy(emailErrorMessageRes = R.string.account_already_exists)
    }

    private fun showProgress() {
        _state.value = State(signUpInProgress = true)
    }

    private fun hideProgress() {
        _state.value = _state.requireValue().copy(signUpInProgress = false)
    }

    private fun showSuccessSignUpMessage() = _showToastEvent.publishEvent(R.string.sign_up_success)

    private fun processStorageException() = _showToastEvent.publishEvent(R.string.storage_error)

    private fun goBack() = _goBackEvent.publishEvent()

    data class State(
        @StringRes val emailErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val lastNameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val firstNameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val emailMismatchErrorMessageRes: Int = NO_ERROR_MESSAGE,
        val signUpInProgress: Boolean = false,
    ) {
        val showProgress: Boolean get() = signUpInProgress
        val enableViews: Boolean get() = !signUpInProgress
    }

    companion object  {
        const val NO_ERROR_MESSAGE = 0
    }

}