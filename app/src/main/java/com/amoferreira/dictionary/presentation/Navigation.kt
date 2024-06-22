package com.amoferreira.dictionary.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amoferreira.dictionary.presentation.ui.containers.SavedWordsContainer
import com.amoferreira.dictionary.presentation.ui.containers.SearchContainer

@Composable
fun Navigation(navController: NavHostController, scaffoldSnackbarHostState: SnackbarHostState) {
    NavHost(navController, startDestination = NavigationItem.Search.route) {
        composable(NavigationItem.Search.route) {
            SearchContainer(scaffoldSnackbarHostState)
        }
        composable(NavigationItem.List.route) {
            SavedWordsContainer(scaffoldSnackbarHostState)
        }
    }
}