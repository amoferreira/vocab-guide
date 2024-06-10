package com.amoferreira.dictionary.presentation.ui.molecules

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amoferreira.dictionary.presentation.NavigationItem

@Composable
fun NavBar(
    navController: NavController
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val tabItems = listOf(NavigationItem.Search, NavigationItem.List)

    NavigationBar {
        tabItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }

}

@Preview
@Composable
fun NavBarPreview() {
    NavBar(rememberNavController())
}