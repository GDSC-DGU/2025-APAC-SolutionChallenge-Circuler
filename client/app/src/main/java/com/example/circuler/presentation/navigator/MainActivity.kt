package com.example.circuler.presentation.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.example.circuler.presentation.ui.splash.SplashScreen
import com.example.circuler.ui.theme.CirculerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            var showSplash by remember { mutableStateOf(true) }
            val counter by remember { mutableIntStateOf(0) }
            val currentCounter by rememberUpdatedState(counter)

            LaunchedEffect(currentCounter) {
                delay(SPLASH_SCREEN_DELAY)
                showSplash = false
            }

            CirculerTheme {
                if (showSplash) {
                    SplashScreen()
                } else {
                    MainScreen(
                        navigator = navigator
                    )
                }
            }
        }
    }

    companion object {
        const val SPLASH_SCREEN_DELAY = 2000L
    }
}
