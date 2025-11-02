package com.example.gamezone2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gamezone2.navigation.AppNavigation
import com.example.gamezone2.navigation.AppNavigation
import com.example.gamezone2.ui.theme.GameZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameZoneTheme {
                AppNavigation()
            }
        }
    }
}