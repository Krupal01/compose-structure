package com.app.compose_structure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose_structure.R
import com.app.compose_structure.common.CustomException
import com.app.compose_structure.common.Result
import com.app.compose_structure.domain.repository.IUserRepository
import com.app.compose_structure.model.UserDataModel
import com.app.compose_structure.model.UserOwnerListModel
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

    private lateinit var userSettingModel: UserDataModel

    init {
        viewModelScope.launch {
            userSettingModel = userRepository.getUserSetting()
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

    fun changeSelectOwnerDialogState(show: Boolean) {
        if (uiState.value.username.isNotEmpty() && uiState.value.ownerList.isEmpty()) {
            fetchUserOwnerList()
        }
        _uiState.update {
            it.copy(
                shouldShowDialog = show
            )
        }
    }

    fun ownerChange(owner: String) {
        _uiState.update {
            it.copy(
                owner = it.ownerList.find { it.customerName == owner }?.customerId ?: "",
                shouldShowDialog = false
            )
        }
    }

    fun fetchUserOwnerList() {
        viewModelScope.launch {
            val result = userRepository.getUserOwnerList(
                username = uiState.value.username.trim(),
                customerId = userSettingModel.settingOwner
            )

            if (result is Result.Success) {
                _uiState.update {
                    it.copy(
                        owner = result.data.firstOrNull()?.customerId ?: "",
                        ownerList = result.data,
                        ownerNameList = result.data.map { it.customerName }
                    )
                }
            } else {
                val error = result as Result.Error
                _uiState.update {
                    it.copy(
                        uiMessage = if (error.exception is CustomException) error.exception.message else it.uiMessage,
//                        uiResMessage = if (error.exception is CustomException) error.exception.messageResId else it.uiResMessage,
                    )
                }
            }
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

            if (uiState.value.owner.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_select_owner
                    )
                }
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = userRepository.authenticateUser(
                username = uiState.value.username.trim(),
                customerId = uiState.value.owner.trim(),
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
    val username: String = "",
    val password: String = "",
    val owner: String = "",

    val isLoading: Boolean = false,
    val uiResMessage: Int? = null,
    val uiMessage: String? = null,

    val ownerList: List<UserOwnerListModel> = emptyList(),
    val ownerNameList: List<String> = emptyList(),

    val shouldShowDialog: Boolean = false,

    val isLoginSuccess: Boolean = false
)