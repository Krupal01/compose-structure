package com.app.compose_structure.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.app.compose_structure.presentation.screens.SplashScreen
import com.app.compose_structure.theme.SplashTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SplashTheme {
                val context = LocalContext.current
                LaunchedEffect(key1 = true) {
                    delay(3000)
                    context.startActivity(Intent(context, MainActivity::class.java))
                    finish()
                }
                SplashScreen()
            }
        }
    }

}

