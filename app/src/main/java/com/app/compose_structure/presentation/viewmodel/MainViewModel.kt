package com.app.compose_structure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.app.compose_structure.domain.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {
    fun isUserLogin() = runBlocking { userRepository.isLogin() }

}

data class UiMessage(
    val message: String? = null,
    val resMessage: Int? = null,
    val params: Array<String>? = null,
    val uiAction: (() -> Unit)? = null,
    val uiDismissAction: (() -> Unit)? = null,
    val uiActionCancel: (() -> Unit)? = null
)