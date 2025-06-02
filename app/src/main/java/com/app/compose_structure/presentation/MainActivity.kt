package com.app.compose_structure.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.compose_structure.common.utils.PermissionUtils
import com.app.compose_structure.presentation.viewmodel.MainViewModel
import com.app.compose_structure.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUserLoggedIn = viewModel.isUserLogin()

        /*val permissionUtils = PermissionUtils(this)
        permissionUtils.checkAndRequestPermissions(
            context = this,
            permissions = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    listOf(
                        android.Manifest.permission.READ_MEDIA_VIDEO,
                        android.Manifest.permission.READ_MEDIA_IMAGES
                    )
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 -> {
                    listOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
                else -> {
                    emptyList()
                }
            },
            onGranted = {
                // All permissions granted
            },
            onDenied = {
                // At least one permission denied temporarily
            },
            onBlocked = {
                // At least one permission permanently denied
                PermissionUtils.openAppSettings(this)
            }
        )*/

        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBaseApp(
                        isUserLoggedIn = isUserLoggedIn
                    )
                }
            }
        }
    }
}