package com.amoferreira.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.amoferreira.dictionary.presentation.ui.screen.MainScreen
import com.amoferreira.dictionary.presentation.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DictionaryTheme {
                MainScreen(navController = navController)
            }
        }
    }
}