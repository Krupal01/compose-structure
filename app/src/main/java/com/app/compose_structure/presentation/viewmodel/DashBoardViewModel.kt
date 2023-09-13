package com.app.compose_structure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose_structure.R
import com.app.compose_structure.common.CustomException
import com.app.compose_structure.common.Result
import com.app.compose_structure.domain.repository.IUserRepository
import com.app.compose_structure.model.FillUserOptionDataModel
import com.app.compose_structure.model.UserDataModel
import com.app.compose_structure.model.UserOwnerListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashBoardUiState())
    val uiState = _uiState.asStateFlow()

    private lateinit var userSetting: UserDataModel

    init {
        viewModelScope.launch {
            userSetting = userRepository.getUserSetting()
            _uiState.update {
                it.copy(
                    owner = userSetting.owner
                )
            }
        }
        fetchFillUserOptionData()
    }

    private fun fetchUserOwnerList() {

        viewModelScope.launch {
            val result = userRepository.getUserOwnerList(
                username = userSetting.username,
                customerId = userSetting.settingOwner
            )

            if (result is Result.Success) {
                _uiState.update {
                    it.copy(
                        owner = userSetting.owner,
                        ownerName = result.data.find { it.customerId ==  userSetting.owner}?.customerName ?: "",
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

    private fun fetchFillUserOptionData() {

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val result = userRepository.getFillUserOptionData(
                username = userSetting.username,
                customerId = uiState.value.owner.trim()
            )

            if (result is Result.Success) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        dashboardMenuList = DashboardOptions.getAllOptions(result.data)
                    )
                }
                fetchOwnerDetails()
            } else {
                val error = result as Result.Error
                _uiState.update {
                    it.copy(
                        isLoading = false,
//                        dashboardMenuList = DashboardOptions.getAllOptionsDisable(),
                        uiMessage = if (error.exception is CustomException) error.exception.message else it.uiMessage,
//                        uiResMessage = if (error.exception is CustomException) error.exception.messageResId else it.uiResMessage,
                    )
                }
            }
        }
    }

    private fun fetchOwnerDetails() {
        viewModelScope.launch {
            userRepository.getFillOwner(
                username = userSetting.username,
                customerId = userSetting.owner
            )
            userRepository.getFillCompInfo(
                username = userSetting.username,
                customerId = userSetting.owner
            )
            userRepository.getListLocation(
                username = userSetting.username,
                customerId = userSetting.owner
            )
            userRepository.getMainTypesList(
                username = userSetting.username,
                customerId = userSetting.owner
            )
            userRepository.getCountryList(
                lng = 1,
                customerId = userSetting.owner
            ) // 1 is for English
        }
    }

    fun changeSelectOwnerDialogState(show: Boolean) {
        if (uiState.value.ownerList.isEmpty()) {
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
                ownerName = it.ownerList.find { it.customerName == owner }?.customerName ?: "",
                shouldShowDialog = false
            )
        }
        fetchFillUserOptionData()
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

    fun onLogOut() {
        viewModelScope.launch {
            userRepository.logout()
        }
        _uiState.update {
            it.copy(isLogout = true)
        }
    }

    fun onReceptionClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isUseEAN128 = userRepository.getOwnerPref().useEAN128 ?: false
                )
            }
        }
    }

}

data class DashBoardUiState(
    val owner: String = "",
    val ownerName : String = "",

    val isLoading: Boolean = false,
    val uiResMessage: Int? = null,
    val uiMessage: String? = null,

    val isUseEAN128: Boolean? = null,

    val ownerList: List<UserOwnerListModel> = emptyList(),
    val ownerNameList: List<String> = emptyList(),
    val dashboardMenuList: List<DashboardOptions> = emptyList(),

    val shouldShowDialog: Boolean = false,
    val isLogout: Boolean = false,
)

data class DashboardOptions(
    val labelName: String,
    val iconImg: Int,
    val bgImg: Int,
    var isEnable: Boolean
) {
    companion object {

        fun getAllOptions(fillUserOptionDataModel: FillUserOptionDataModel?): List<DashboardOptions> {
            val listOfOption = mutableListOf<DashboardOptions>()
            listOfOption.addAll(
                arrayOf(
                    DashboardOptions(
                        DashboardOptionsLabel.RECEPTION.labelName,
                        R.drawable.ic_reception,
                        R.drawable.bg_purple,
                        true
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.PUT_AWAY.labelName,
                        R.drawable.ic_putaway,
                        R.drawable.bg_cyan,
                        true
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.EXPEDITION.labelName,
                        R.drawable.ic_expedition,
                        R.drawable.bg_green,
                        true
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.SHIPPING.labelName,
                        R.drawable.ic_shipping,
                        R.drawable.bg_yellow,
                        fillUserOptionDataModel?.shipRegTransYN ?: false
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.RETURN.labelName,
                        R.drawable.ic_return,
                        R.drawable.bg_sky_blue,
                        true
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.LOCATION_TRANSFER.labelName,
                        R.drawable.ic_location_transfer,
                        R.drawable.bg_orange,
                        true
                    ),
                    DashboardOptions(
                        DashboardOptionsLabel.INVENTORY.labelName,
                        R.drawable.ic_inventory,
                        R.drawable.bg_pink,
                        fillUserOptionDataModel?.inventoryYN ?: false
                    )
                )
            )

            return listOfOption
        }

        /* fun getAllOptionsDisable(): List<DashboardOptions> {
             val listOfOption = mutableListOf<DashboardOptions>()
             listOfOption.addAll(
                 arrayOf(
                     DashboardOptions(
                         DashboardOptionsLabel.RECEPTION.labelName,
                         R.drawable.ic_reception,
                         R.drawable.bg_purple,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.PUT_AWAY.labelName,
                         R.drawable.ic_putaway,
                         R.drawable.bg_cyan,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.EXPEDITION.labelName,
                         R.drawable.ic_expedition,
                         R.drawable.bg_green,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.SHIPPING.labelName,
                         R.drawable.ic_shipping,
                         R.drawable.bg_yellow,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.RETURN.labelName,
                         R.drawable.ic_return,
                         R.drawable.bg_sky_blue,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.LOCATION_TRANSFER.labelName,
                         R.drawable.ic_location_transfer,
                         R.drawable.bg_orange,
                         false
                     ),
                     DashboardOptions(
                         DashboardOptionsLabel.INVENTORY.labelName,
                         R.drawable.ic_inventory,
                         R.drawable.bg_pink,
                         false
                     )
                 )
             )

             return listOfOption
         }*/
    }
}

enum class DashboardOptionsLabel(
    val labelName: String,
) {
    RECEPTION("Reception"),
    PUT_AWAY("Put Away"),
    EXPEDITION("Expedition"),
    SHIPPING("Shipping"),
    RETURN("Return"),
    LOCATION_TRANSFER("Location Transfer"),
    INVENTORY("Inventory");

}

