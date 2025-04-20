package com.example.circuler.presentation.navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import com.example.circuler.presentation.dummy.ReqresRoute
import com.example.circuler.ui.theme.CirculerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CirculerTheme {
                ReqresRoute(
                    paddingValues = PaddingValues(),
                    navigateUp = {}
                )
            }
        }
    }
}
