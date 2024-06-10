package com.amoferreira.dictionary.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    data object Search : NavigationItem("Search", Icons.Outlined.Search, "Search")
    data object List : NavigationItem("List", Icons.Outlined.List, "List")
}