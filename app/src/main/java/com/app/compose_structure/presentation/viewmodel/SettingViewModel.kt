package com.app.compose_structure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose_structure.R
import com.app.compose_structure.domain.repository.IUserRepository
import com.app.compose_structure.model.UserDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingUIState())
    val uiState = _uiState.asStateFlow()

    private lateinit var userSettingModel: UserDataModel

    init {
        viewModelScope.launch {

            userSettingModel = userRepository.getUserSetting()

            _uiState.update {
                it.copy(
                    username = userSettingModel.settingUsername,
                    owner = userSettingModel.settingOwner,
                    webServiceUrl = userSettingModel.settingWebUrl,
                    isMultiScanning = userSettingModel.isMultiScanEnable,
                    isScandiTestingKey = userSettingModel.isUseScandiTestingKey,
                )
            }
        }
    }

    fun usernameChange(username: String) {
        _uiState.update {
            it.copy(
                username = username
            )
        }
    }

    fun webServiceUrl(url: String) {
        _uiState.update {
            it.copy(
                webServiceUrl = url
            )
        }
    }

    fun ownerChange(owner: String) {
        _uiState.update {
            it.copy(
                owner = owner,
                shouldShowDialog = false
            )
        }
    }

    fun changeMultiScaning(value: Boolean) {
        _uiState.update {
            it.copy(
                isMultiScanning = value
            )
        }
    }


    fun changeTestingKey(value: Boolean) {
        _uiState.update {
            it.copy(
                isScandiTestingKey = value
            )
        }
    }

    fun save() {
        viewModelScope.launch {


            if (uiState.value.username.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_enter_valid_username
                    )
                }
                return@launch
            }

            if (uiState.value.owner.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_enter_valid_owner
                    )
                }
                return@launch
            }

            if (uiState.value.webServiceUrl.isEmpty()) {
                _uiState.update {
                    it.copy(
                        uiResMessage = R.string.please_enter_valid_web_url
                    )
                }
                return@launch
            }

            userRepository.storeUserSetting(
                userSettingModel.copy(
                    settingUsername = uiState.value.username,
                    settingOwner = uiState.value.owner,
                    settingWebUrl = uiState.value.webServiceUrl,
                    isMultiScanEnable = uiState.value.isMultiScanning,
                    isUseScandiTestingKey = uiState.value.isScandiTestingKey,
                )
            )

            _uiState.update {
                it.copy(isSaved = true)
            }
        }
    }

    fun dismissErrorDialog() {
        _uiState.update {
            it.copy(uiResMessage = null)
        }
    }
}

data class SettingUIState(
    val username: String = "",
    val webServiceUrl: String = "",
    val owner: String = "",
    val isMultiScanning: Boolean = false,
    val isScandiTestingKey: Boolean = false,

    val uiResMessage: Int? = null,

    val shouldShowDialog: Boolean = false,

    val isSaved: Boolean = false
)