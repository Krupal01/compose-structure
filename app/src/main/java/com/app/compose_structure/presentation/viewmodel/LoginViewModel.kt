package com.app.compose_structure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose_structure.R
import com.app.compose_structure.common.CustomException
import com.app.compose_structure.common.Result
import com.app.compose_structure.domain.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {

        }
    }

    fun usernameChange(username: String) {
        _uiState.update {
            it.copy(
                username = username
            )
        }
    }

    fun passwordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            if (uiState.value.username.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_enter_valid_username
                    )
                }
                return@launch
            }

            if (uiState.value.password.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_enter_valid_password
                    )
                }
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = userRepository.login(
                email = uiState.value.username.trim(),
                password = uiState.value.password
            )

            if (result is Result.Success) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccess = true
                    )
                }
            } else {
                val error = result as Result.Error
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        uiMessage = if (error.exception is CustomException) error.exception.message else it.uiMessage,
//                        uiResMessage = if (error.exception is CustomException) error.exception.messageResId else it.uiResMessage,
                    )
                }
            }
        }
    }

    fun dismissProgressDialog() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }

    fun dismissErrorDialog() {
        _uiState.update {
            it.copy(uiResMessage = null, uiMessage = null)
        }
    }
}

data class LoginUIState(
    val username: String = "eve.holt@reqres.in",
    val password: String = "cityslicka",

    val isLoading: Boolean = false,
    val uiResMessage: Int? = null,
    val uiMessage: String? = null,

    val shouldShowDialog: Boolean = false,

    val isLoginSuccess: Boolean = false
)