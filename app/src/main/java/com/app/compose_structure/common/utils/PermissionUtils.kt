package com.app.compose_structure.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

        val notGranted = permissions.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }

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
    }
}
