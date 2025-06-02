package com.app.compose_structure.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/*
How to use,
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionUtils = PermissionUtils(this)
        permissionUtils.checkAndRequestPermissions(
            context = this,
            permissions = listOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ),
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
        )
    }
 */

class PermissionUtils(
    caller: ActivityResultCaller
) {

    private var onGranted: (() -> Unit)? = null
    private var onDenied: (() -> Unit)? = null
    private var onBlocked: (() -> Unit)? = null
    private var context: Context? = null
    private var permissions: List<String> = emptyList()

    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        caller.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            val ctx = context ?: return@registerForActivityResult

            val deniedList = results.filterValues { !it }.keys
            val blockedList = deniedList.filter { permission ->
                (ctx as? Activity)?.let {
                    androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
                } ?: false
            }

            when {
                deniedList.isEmpty() -> onGranted?.invoke()
                blockedList.isNotEmpty() -> onBlocked?.invoke()
                else -> onDenied?.invoke()
            }
        }

    fun checkAndRequestPermissions(
        context: Context,
        permissions: List<String>,
        onGranted: () -> Unit,
        onDenied: () -> Unit,
        onBlocked: () -> Unit
    ) {
        this.context = context
        this.permissions = permissions
        this.onGranted = onGranted
        this.onDenied = onDenied
        this.onBlocked = onBlocked

        val notGranted = getUngrantedPermissions(context, permissions)

        if (notGranted.isEmpty()) {
            onGranted()
        } else {
            permissionLauncher.launch(notGranted.toTypedArray())
        }
    }

    companion object {
        fun openAppSettings(context: Context) {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        fun arePermissionsGranted(context: Context, permissions: List<String>): Boolean {
            return permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        }
        fun getUngrantedPermissions(context: Context, permissions: List<String>): List<String> {
            return permissions.filter {
                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
            }
        }
    }
}


/*@Composable
fun HomeScreen() {

    PermissionHandler(
        permissions = ListOfPermissions,
        onGranted = {
            // All permissions granted
        },
        onDenied = { deniedList ->
            // Show message or handle denial
        },
        onPermanentlyDenied = { permanentlyDenied ->
            // Show dialog to redirect user to app settings
        }
    )

    // Your UI
}*/

@Composable
fun PermissionHandler(
    permissions: List<String>,
    onGranted: () -> Unit,
    onDenied: (deniedPermissions: List<String>) -> Unit,
    onPermanentlyDenied: (permanentlyDeniedPermissions: List<String>) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val ungrantedPermissions = remember {
        PermissionUtils.getUngrantedPermissions(context, permissions)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { resultMap ->
        val denied = resultMap.filterValues { !it }.keys.toList()

        if (denied.isEmpty()) {
            onGranted()
        } else {
            val permanentlyDenied = denied.filter { permission ->
                activity?.let {
                    !ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
                } ?: false
            }

            when {
                permanentlyDenied.isNotEmpty() -> onPermanentlyDenied(permanentlyDenied)
                else -> onDenied(denied)
            }
        }
    }

    LaunchedEffect(Unit) {
        if (ungrantedPermissions.isEmpty()) {
            onGranted()
        } else {
            permissionLauncher.launch(ungrantedPermissions.toTypedArray())
        }
    }
}


@Composable
fun PermissionDialog(
    title: String,
    permissions: List<String>,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            androidx.compose.material3.Text(text = title)
        },
        text = {
            Column {
                androidx.compose.material3.Text(message)
                Spacer(modifier = Modifier.height(8.dp))
                permissions.forEach {
                    androidx.compose.material3.Text(text = "• $it", style = androidx.compose.material3.MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onConfirm) {
                androidx.compose.material3.Text(confirmText)
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                androidx.compose.material3.Text("Cancel")
            }
        }
    )
}
