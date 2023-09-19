package com.app.compose_structure.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.compose_structure.R
import com.app.compose_structure.common.Result
import com.app.compose_structure.data.remote.model.DataItem
import com.app.compose_structure.domain.repository.IUserRepository
import com.app.compose_structure.domain.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    val color = savedStateHandle.getStateFlow("color" , 0xFFFFFF)

    var actionColor by mutableStateOf(0xFFFFFFFF)
        private set

    init {
        getUserList()
    }

    fun changeActionColor(){
        actionColor = Random.nextLong(0xFFFFFFFF)
    }

    fun changeColor(){
        savedStateHandle["color"] = Random.nextLong(0xFFFFFFFF)
    }

    fun getUserList(){
        viewModelScope.launch {
            val result = userRepository.getUserList(page = 2)

            if (result is Result.Success){
                _uiState.update {
                    it.copy(
                        userList = result.data?.data ?: emptyList()
                    )
                }
            }else{
                _uiState.update {
                    it.copy(
                        uiMessage = UiMessage(
                            message = (result as Result.Error).exception?.message
                        )
                    )
                }
            }
        }
    }

    fun showDialog(){
        _uiState.update {
            it.copy(
                uiMessage = UiMessage(
                    resMessage = R.string.no_refresh_for_page_s,
                    params = arrayOf("3")
                )
            )
        }
    }

    fun dismissErrorDialog() {
        _uiState.update {
            it.copy(
                uiMessage = null
            )
        }
    }

    fun changeProgressState(show: Boolean = false) {
        _uiState.update {
            it.copy(
                isLoading = show
            )
        }
    }

    fun dismissProgressDialog() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }
}

data class DashboardUiState(
    val isLoading: Boolean = false,
    val uiMessage: UiMessage? = null,
    val userList : List<DataItem?> = emptyList(),
)